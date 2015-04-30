(ns mongoex.api
  "Web interface/api"
  (:use compojure.core)
  (:require [hiccup.core :as hiccup]
            [mongoex.db :as db]))

(defn generate-html [user-data]
  "Generate html form of user data"
  ;; gen is a function that takes a doc/map and spits it into
  ;; <ul> <li> key: value </li> </ul> form
  (let [gen (fn [doc]
              (hiccup/html [:ul
                            (for [[k v] doc]
                              [:il (str (name k) ": " v)])]))]
    ;;if its a list of docs we map gen on all docs and create a single string
    (if (seq? user-data)
      (apply str (map gen user-data))
      (gen user-data) ;;or just generate for a single doc
      )))

(defn user-response [user-data & [status]]
  "Create a response of the Web API for user data"
  {:status (or status 200)
   :headers {"Content-Type" "text/html"}
   :body  (generate-html user-data)})

(defroutes app
  (GET "/" [] "OK")
  (GET "/users" [] (user-response (db/users))))
