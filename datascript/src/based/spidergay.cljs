(ns based.spidergay
  (:require [datascript.core :as d]))

(def schema
  {:name {:db/unique :db.unique/identity}

   :alias {:db/unique :db.unique/value
           :db/cardinality :db.cardinality/many}

   :spouse {:db/cardinality :db.cardinality/many
            :db/type :db.type/ref}

   :child {:db/type :db.type/ref
           :db/cardinality :db.cardinality/many}})