(ns mongoex.api-test
  (:require [mongoex.api :refer :all]
            [midje.sweet :refer :all]
            [clj-http.client :as client]
            [hiccup.core :as hiccup]))

(def test-user
  (array-map :name "Anthony Stallman"
             :location "Los Angeles"
             :occupation "Business Analyst"))

(facts "About a simple Web API using MongoDB"
       (fact "User data gets generated as html"
             (generate-html test-user) =>
             (str "<ul>"
                  "<li>name: Anthony Stallman</li>"
                  "<li>location: Los Angeles</li>"
                  "<li>occupation: Business Analyst</li>"
                  "</ul>"))
       (fact "A succesful query response would be"
             (user-response test-user) =>
             {:status 200
              :headers {"Content-Type" "text/html"}
              :body (str "<ul>"
                         "<li>name: Anthony Stallman</li>"
                         "<li>location: Los Angeles</li>"
                         "<li>occupation: Business Analyst</li>"
                         "</ul>")}))
