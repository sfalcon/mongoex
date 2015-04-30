(ns mongoex.core
  (:use mongoex.api
        ring.adapter.jetty)
  (:gen-class))

(defn -main
  ""
  [& args]
  (run-jetty #'app {:port 1234}))
