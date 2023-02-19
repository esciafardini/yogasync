(ns main.yogasync)

(defn plus [x y]
  (+ x y))

(defn divide [x y]
  (/ x y))

(defn run [& _args]
  (println (format "2 + 2 is %s" (plus 2 2)))
  (println (format "4 / 2 is %s" (divide 4 2))))
