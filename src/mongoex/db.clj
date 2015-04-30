(ns mongoex.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

(def conn (mg/connect))

(defn users []
  "Retrieve all users from the Example DB"
  (let [db (mg/get-db conn "tests-db")
        coll "users"]
    (mc/find-maps db coll)))
