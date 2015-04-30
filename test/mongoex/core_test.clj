(ns mongoex.core-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [mongoex.core :refer :all]
            [mongoex.db   :refer :all]
            [mongoex.api  :refer :all]
            [midje.sweet  :refer :all]
            [monger.core  :as mg]
            [monger.collection :as mc]
            [clj-http.client :as client]))


(facts "About the app"
       (fact "The endpoint is available"
             (let [response (app (mock/request :get "/"))]
               (:status response) => 200))
       (fact "Querying for users yields a list of documents"
             (let [response (app (mock/request :get "/users"))]
               (response :body) => (contains "Tony Manero"))))
