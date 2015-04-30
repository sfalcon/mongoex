(ns mongoex.db-test
  (:require [mongoex.db :refer :all]
            [midje.sweet :refer :all]
            [monger.core :as mg]
            [monger.collection :as mc]))

(let [connection (mg/connect)
      test-db (mg/get-db connection "tests-db")
      test-user {:name "Anthony Stallman"
                 :location "Los Angeles"
                 :occupation "Business Analyst"}]
  (facts "About MongoDB with clojure"
         ;; mg/connect without options => default host+port

         (fact "Server is up and can be connected to"
               (nil? connection) => false)
         (fact "Test database is available"
               (nil? test-db) => false)
         (fact "Insertion works"
               (-> (mc/insert test-db "test" test-user)
                   (.getField "ok")) => 1)
         (fact "It can be found in the db now"
               (-> (mc/find-one-as-map test-db
                                       "test"
                                       {:name (:name test-user)})
                   :name) => "Anthony Stallman")
         (fact "And now deleted"
               (-> (mc/remove test-db "test" {:name (:name test-user)})
                   (.getField "ok")) => 1)
         (mg/disconnect connection)))

(let [test-user {:name "Tony Manero"
                 :location "Malaga"
                 :occupation "Compositor"}]
  (facts "About Collection 'Users'"
         (fact "Querying for users works"
               (-> (users)
                   first
                   :name) =>  (:name test-user))))
