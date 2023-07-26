(ns yogasync.env
  (:require
   [clojure.string :as string]))

(def dbenv (->> (string/split (slurp ".env") #"\n")
                (mapv (fn [s] (string/split s #"=")))
                (mapv (fn [[k v]] [(keyword k) v]))
                (into {})))

(defn get-env-var [k]
  (or (get dbenv k) (System/getenv (name k))))

(comment
  (get-env-var :USER)

  (System/getenv "HOME"))
