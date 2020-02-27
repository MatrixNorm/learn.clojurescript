(ns demo.app
  (:require
   ["/js/bar" :as bar :refer (sum)]
   [clojure.test.check :as tc]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop :include-macros true]))

(def sum-property-1 (prop/for-all [x gen/int
                                 y gen/int]
                                (>= (sum x y) x)))

(tc/quick-check 100 sum-property-1)

(sum 4 7)

(def property
  (prop/for-all [v (gen/vector gen/int)]
                (let [s (sort v)]
                  (and (= (count v) (count s))
                       (or (empty? s)
                           (apply <= s))))))

(tc/quick-check 100 property)
