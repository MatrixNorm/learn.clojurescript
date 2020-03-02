(ns gen-testing-1.sort-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :refer-macros [defspec]]
            [cljs.test :refer (deftest is)]))

(defspec first-element-is-min-after-sorting 
  100
  (prop/for-all [v (gen/not-empty (gen/vector gen/int))]
                (= (apply min v)
                   (first (sort v)))))

(defspec sort-is-idempotent 100
  (prop/for-all [v (gen/vector gen/int)]
                (= (sort v) (sort (sort v)))))