(ns yogasync.core
  (:require
   [muuntaja.core :as m]
   [reitit.ring :as ring]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [ring.adapter.jetty :as ring-jetty]
   [ring.util.response :as res]
   [ring.logger :as logger]
   [yogasync.db :as db]))

(defn create-user [req]
  (let [{:keys [username password teacher admin]} (get req :body-params)
        insert-count (-> (db/insert-user! username password teacher admin)
                         first)]
    (res/response (str "Created " insert-count " user: " username))))

(defn get-users [_]
  (res/response (db/get-users)))

(defn dynamic-path-param-ex [req]
  (res/response (str "You requested to: " (get-in req [:path-params :something]))))

(def app
  (ring/ring-handler
   (ring/router
    [["/"
      ["" (fn [_req] {:body "it works" :status 200})]
      [":something" dynamic-path-param-ex]]
     ["/api"
      ["/users" {:get get-users
                 :post create-user}]]]
    {:data {:muuntaja m/instance
            :middleware [[muuntaja/format-middleware]
                         [logger/wrap-with-logger {:request-keys (conj logger/default-request-keys :body-params :body :request-id)}]]}})))

(defn start []
  (ring-jetty/run-jetty #'app {:port  3777
                               :join? false}))

(def server (start))

(comment
  (.stop server))
