(ns yogasync.db
  (:require [clojure.java.jdbc :as j]
            [honey.sql :as hdb]
            [yogasync.env :as env]))

(def db-spec
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname (env/get-env-var :SUBNAME)
   :user (env/get-env-var :POSTGRESUSER)
   :password (env/get-env-var :PASSWORD)})

(defn fetch [q]
  (j/query db-spec (hdb/format q)))

(defn insert! [q]
  (j/db-do-prepared db-spec (hdb/format q)))

;;
;;USER MODEL

(defn insert-user! [username password teacher admin]
  (insert! {:insert-into [:users]
            :columns [:username :password :teacher :admin]
            :values [[username password teacher admin]]}))

(defn get-users []
  (fetch {:select [:*]
          :from [:users]}))

(defn get-user-by-username [username]
  (-> {:select [:*]
       :from [:users]
       :where [:= username :username]}
      fetch
      first))

;;REPL PLAYGROUND
(comment

  (fetch {:select [:*]
          :from [:users]})

  (get-user-by-username "toddcage")

  (insert-user! "anotherone" "erjdhs" false false)

  (insert! {:insert-into [:users]
            :columns [:username :password :teacher :admin]
            :values [["keithgolden" "tigerstyle" true false]]}))
