(ns mongoex.core-test
  (:require [clojure.test :refer :all]
            [mongoex.core :refer :all]
            [midje.sweet  :refer :all]
            [monger.core  :as mg]
            [monger.collection :as mc]
            [clj-http.client :as client]))

(def test-user
  {:name "Anthony Stallman"
   :location "Los Angeles"
   :occupation "Business Analyst"})

;;first, some tests to familiarize with the basic API
(facts "About MongoDB with clojure"
          ;; mg/connect without options => default host+port
       (let [connection (mg/connect)
             test-db (mg/get-db connection "tests-db")]
         (fact "Server is up and can be connected to"
               (nil? connection) => false)
         (fact "Test database is available"
               (nil? test-db) => false)
         (fact "Insertion works"
               (-> (mc/insert test-db "users" test-user)
                   (.getField "ok")) => 1)
         (fact "It can be found in the db now"
               (-> (mc/find-one-as-map test-db
                                       "users"
                                       {:name (:name test-user)})
                   :name) => "Anthony Stallman")
         (fact "And now deleted"
               (-> (mc/remove test-db "users" {:name (:name test-user)})
                   (.getField "ok")) => 1)))


(facts "About a simple Web API using MongoDB"
       (fact "Endpoint is available"
             ((client/get "http://127.0.0.1:1234/") :status) => 200 ))
