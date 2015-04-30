(defproject mongoex "0.1.0-SNAPSHOT"
  :description "An example of clojure interaction with MongoDB"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.novemberain/monger "2.0.0"]
                 [clj-http "1.1.1"]
                 [compojure "1.3.3"]
                 [ring "1.3.2"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-marginalia "0.8.0"]
            [lein-ring "0.9.3"]]
   :aliases {"autotest" ["midje" ":autotest"]}
  :main ^:skip-aot mongoex.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[midje "1.6.3"]]}})
