(ns pom-helper.core
  (:gen-class)
  (:require [clojure.xml :as xml
             clojure.zip :as zip]))

(defn parse-str [s]
  (let [input (java.io.ByteArrayInputStream. (.getBytes s))]
    (-> input (xml/parse) (zip/xml-zip))))
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
