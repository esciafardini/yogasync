(ns yogasync.core
  (:require
   [muuntaja.core :as m]
   [reitit.ring :as ring]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [ring.adapter.jetty :as ring-jetty]
   [ring.util.response :as res]
   [ring.middleware.cors :refer [wrap-cors]]
   [ring.logger :as logger]
   [yogasync.db :as db]))

(defn cors-handler [handler]
  (wrap-cors handler :access-control-allow-origin [#"http://localhost:5003"]
             :access-control-allow-methods [:get :put :post :delete]))

(defn create-user [req]
  (let [{:keys [username password teacher admin]} (get req :body-params)
        insert-count (-> (db/insert-user! username password teacher admin)
                         first)]
    (res/response (str "Created " insert-count " user: " username))))

(defn get-users [_]
  (res/response (db/get-users)))

(defn dynamic-path-param-example [req]
  (res/response (str "You requested to: " (get-in req [:path-params :dynamic-arg]))))

(defn redirect-example [req]
  (let [q (get-in req [:path-params :q])
        url (str "https://www.google.com/search?q=" q)]
    (res/redirect url 307)))

(def app
  (ring/ring-handler
   (ring/router
    [["/"
      ["" (fn [_req] {:body "it works" :status 200})]
      ["redirect/:q" redirect-example]]
     ["/api"
      ["/dynamic/:dynamic-arg" dynamic-path-param-example]
      ["/users" {:get get-users
                 :post create-user}]]]
    {:data {:muuntaja m/instance
            :middleware [[muuntaja/format-middleware]
                         [cors-handler]
                         [logger/wrap-with-logger {:request-keys (conj logger/default-request-keys :body-params :body :request-id)}]]}})))

(defn start []
  (ring-jetty/run-jetty #'app {:port  3777
                               :join? false}))

(def server (start))

(comment
  (.stop server))
