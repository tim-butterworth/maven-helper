(ns pom-helper.core
;  (:gen-class)
  (:require [clojure.xml :as xml]
   :require [clojure.zip :as zip]))

(defn parse-str [s]
  (let [input (java.io.ByteArrayInputStream. (.getBytes (slurp s)))]
    (-> input (xml/parse) (zip/xml-zip))))

(defn find-in-list [key lst]
  (if (empty? lst)
    nil
    (let [current (first lst)]
      (if (= key (name (current :tag)))
        current
        (find-in-list key (rest lst))))))

(defn unwrap-if-needed [data]
  (if (or 
       (sequential? data)
       (empty? data))
    data
    (data :content)))

(defn path-find [path data]
  (let [node (unwrap-if-needed data)]
    (if (empty? path)
      node
      (let [key (first path)]
        (path-find (rest path) (find-in-list key node))))))
;/Users/timothybutterworth/.m2/repository/org/springframework/spring-jpa/2.0.8/spring-jpa-2.0.8.pom

;(defn -main [& args] (println "Hello, World!"))




