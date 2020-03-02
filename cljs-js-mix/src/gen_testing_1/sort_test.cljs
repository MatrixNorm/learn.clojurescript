(ns gen-testing-1.sort-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop :include-macros true]))

(def sort-idempotent-prop
  (prop/for-all [v (gen/vector gen/int)]
                (= (sort v) (sort (sort v)))))

(tc/quick-check 100 sort-idempotent-prop)