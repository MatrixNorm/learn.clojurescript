(ns based.departament
  (:require [datascript.core :as d]))

(def schema {:maker/email {:db/unique :db.unique/identity}
             :car/model {:db/unique :db.unique/identity}
             :car/maker {:db/type :db.type/ref}
             :car/colors {:db/cardinality :db.cardinality/many}})

(def conn (d/create-conn schema))

(d/transact! conn [{:maker/email "ceo@bmw.com"
                    :maker/name "BMW"}
                   {:car/model "E39530i"
                    :car/maker [:maker/email "ceo@bmw.com"]
                    :car/name "2003 530i"}])

(d/entity @conn [:car/model "E39530i"])
(:maker/name (d/entity @conn [:maker/email "ceo@bmw.com"]))

(d/transact! conn [{:car/model "E39520i"
                    :car/maker [:maker/email "ceo@bmw.com"]
                    :car/name "2003 520i"}])

(d/q '[:find [?name ...]
       :where
       [?c :car/maker [:maker/email "ceo@bmw.com"]]
       [?c :car/name ?name]]
     @conn)

(d/transact! conn [{:maker/email "ceo@bmw.com"
                    :maker/name "BMW Motors"}])

