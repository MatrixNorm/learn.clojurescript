(ns based.spidergay
  (:require [datascript.core :as d]))

(def schema
  {:name {:db/unique :db.unique/identity}

   :alias {:db/unique :db.unique/value
           :db/cardinality :db.cardinality/many}

   :spouse {:db/type :db.type/ref
            :db/cardinality :db.cardinality/many}

   :child {:db/type :db.type/ref
           :db/cardinality :db.cardinality/many}})

(def initial-data
  [{:name "Peter Parker" :gender "M" :alias ["Spider-Man" "Spidey"]}
   {:name "Richard Parker" :gender "M" :spouse {:name "Mary Parker"}}
   {:name "Mary Parker" :gender "F" :spouse {:name "Richard Parker"}}
   {:name "Ben Parker" :gender "M" :spouse {:name "May Parker"}}
   {:name "May Parker" :gender "F" :spouse {:name "Ben Parker"}}
   {:name "Richard Parker" :child {:name "Peter Parker"}}
   {:name "Mary Parker" :child {:name "Peter Parker"}}
   {:child  [{:name "Ben Parker"}
             {:name "Richard Parker"}]
    :gender "M"}
   {:child  [{:name "Ben Parker"}
             {:name "Richard Parker"}]
    :gender "F"}])

(def conn (d/create-conn schema))

(first (vec (:eavt @conn)))

(d/transact! conn initial-data)

(d/q '[:find [?name ...]
       :where
       [_ :name ?name]]
     @conn)