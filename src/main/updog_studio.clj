(ns main.updog-studio
  (:require
   [cheshire.core :as cheshire]
   [clj-http.client :as client]
   [main.secrets :as secrets])
  (:import
   (java.time Instant LocalDateTime ZoneId)
   (java.time.format DateTimeFormatter)
   (java.util Locale)))

(defn convert-epoch-second-to-readable-time
  "Makes a readable string for the epoch fetched from Updog's API
   Java interop is not so nice to read"
  [epoch]
  (let [instant (Instant/ofEpochSecond epoch)]
    (->> (ZoneId/of "America/New_York")
         (LocalDateTime/ofInstant instant)
         (.format (DateTimeFormatter/ofPattern "hh:mm a" Locale/US)))))

(defn convert-epoch-second-to-epoch-ms [epoch]
  (* 1000 epoch))

(def now (-> (Instant/now)
             .getEpochSecond))

(def midnight (-> (Instant/now)
                  (.truncatedTo (java.time.temporal.ChronoUnit/DAYS))
                  .getEpochSecond))

(defn get-updog-info []
  (client/get
   (format "https://api.glofox.com/2.0/events?end=%s&include=trainers,facility,program,users_booked&page=1&private=false&sort_by=time_start&start=%s" (str midnight) (str now))
   {:query-params {:start (str now)
                   :end (str (+ now 43200)) ;12 hours until something better
                   :include "trainers"
                   :page "1"
                   :private "false"}
    :headers {:authorization secrets/updog-auth-token
              :origin "https://app.glofox.com"}}))

(def relevant-data
  (-> (get-updog-info)
      :body
      cheshire/parse-string
      (get "data")))

(defn transform-keys [m]
  (into {} (map (fn [[k v]] [(keyword k) v]) m)))

(->> relevant-data
     (map transform-keys)
     (map (fn [m] (update m :trainers_obj (fn [t]
                                            (let [{:keys [first_name last_name]} (transform-keys (first t))]
                                              (format "%s %s" first_name last_name))))))
     (map (fn [m] (assoc m
                         :teacher (:trainers_obj m)
                         :studio "Updog Studios"
                         :epoch-ms (convert-epoch-second-to-epoch-ms (:time_start m))
                         :time (convert-epoch-second-to-readable-time (:time_start m)))))
     (map (fn [m] (select-keys m [:epoch-ms :studio :teacher :name :time])))
     (sort-by :epoch-ms))
