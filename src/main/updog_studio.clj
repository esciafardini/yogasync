(ns main.updog-studio
  (:require
   [cheshire.core :as cheshire]
   [clj-http.client :as client])
  (:import (java.time Instant)))

(defn convert-epoch-second-to-readable-time
  "Makes a readable string epochSecond"
  [epoch]
  (str (Instant/ofEpochSecond epoch)))

(def now
  (.getEpochSecond (Instant/now)))

(def midnight
  (.getEpochSecond (.truncatedTo (Instant/now) (java.time.temporal.ChronoUnit/DAYS))))

(defn get-updog-info []
  (client/get
   (format "https://api.glofox.com/2.0/events?end=%s&include=trainers,facility,program,users_booked&page=1&private=false&sort_by=time_start&start=%s" (str midnight) (str now))
   {:query-params {:end "1678078799"
                   :include "trainers"
                   :page "1"
                   :private "false"
                   :start "1676178000"}
    :headers {:authorization "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJfIiwiZXhwIjoxNjc5MjgyOTEwLCJpYXQiOjE2NzY4NjM3MTAsImlzcyI6Il8iLCJ1c2VyIjp7Il9pZCI6Imd1ZXN0IiwibmFtZXNwYWNlIjoidXBkb2dzdHVkaW9zIiwiYnJhbmNoX2lkIjoiNjIzZTM3ODJjY2I3MjUwNTQxMTc2M2U1IiwiZmlyc3RfbmFtZSI6Ikd1ZXN0IiwibGFzdF9uYW1lIjoiVXNlciIsInR5cGUiOiJHVUVTVCIsImlzU3VwZXJBZG1pbiI6ZmFsc2V9fQ.MBvC68CN9BjQF11WzRS5ZAQFh7LZNfmUE0eIf2C1-aM"
              :origin "https://app.glofox.com"}}))

(def relevant-data
  (-> (get-updog-info)
      :body
      cheshire/parse-string
      (get "data")))

(defn transform-keys [m]
  (into {} (map (fn [[k v]] [(keyword k) v]) m)))

(defn filter-down-to-today [classes]
  (filter (fn [m] (and (> now (get m "time_start"))
                       (< midnight (get m "time_start"))))
          classes))

(->> relevant-data
     filter-down-to-today
     (map transform-keys)
     (map (fn [m] (update m :trainers_obj (fn [t]
                                            (let [{:keys [first_name last_name]} (transform-keys (first t))]
                                              (format "%s %s" first_name last_name))))))
     (map (fn [m] (assoc m
                         :teacher (:trainers_obj m)
                         :studio "Updog Studios"
                         :time (convert-epoch-second-to-readable-time (:time_start m)))))
     (map (fn [m] (select-keys m [:studio :teacher :name :time]))))
