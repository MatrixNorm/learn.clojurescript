(ns based.batman
  (:require [datascript.core :as d]))

(def schema {:name {:db/unique :db.unique/identity}
             :alias {:db/unique :db.unique/value
                     :db/cardinality :db.cardinality/many}
             :powers {:db/cardinality :db.cardinality/many}
             :weapons {:db/cardinality :db.cardinality/many}
             :nemesis {:db/type :db.type/ref
                       :db/cardinality :db.cardinality/many}})

(def conn (d/create-conn schema))

(d/transact! conn [{:name "Bruce Wayne"
                    :age 32
                    :gender "M"
                    :powers ["Rich", "Deep Voice"]
                    :weapons ["Belt", "Batmobile"]
                    :nemesis [{:name "Joker"} {:name "Fagman"}]}
                   {:name "Joker"
                    :powers ["Makeup" "Funny"]}])

(d/q '[:find [?name ...]
       :where
       [_ :name ?name]]
     @conn)