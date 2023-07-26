(ns yogasync.env
  (:require
   [clojure.string :as string]))

(def dbenv (->> (string/split (slurp ".env") #"\n")
                (mapv (fn [s] (string/split s #"=")))
                (reduce (fn [acc [k v]] (assoc acc (keyword k) v)) {})))

(defn get-env-var [k]
  (or (get dbenv k) (System/getenv (name k))))

(comment
  (get-env-var :USER)

  (System/getenv "HOME"))
