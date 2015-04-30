(ns mongoex.api
  "Web interface/api"
  (:use compojure.core)
  (:require [hiccup.core :as hiccup]))

(defn generate-html [user-data]
  (hiccup/html [:ul
                (for [[k v] user-data]
                  [:il (str (name k) ": " v)])]))

(defn user-response [user-data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "plain/text"}
   :body  (generate-html user-data)})
