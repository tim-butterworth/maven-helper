(ns pom-helper.core-test
  (:require [clojure.test :refer :all]
            [pom-helper.core :refer :all]))

(deftest a-test
  (testing "find-in-list"
    (is (= nil (find-in-list "kewl" [])))
    (is (= {:tag :kewl} (find-in-list "kewl" [{:tag :kewl}])))
    (is (= {:tag :kewl} (find-in-list "kewl" [{:tag :not-it-1}
                                              {:tag :not-it-2}
                                              {:tag :not-it-3}
                                              {:tag :kewl}])))
    (is (= {:tag :kewl} (find-in-list "kewl" [{:tag :not-it-1}
                                              {:tag :kewl}
                                              {:tag :not-it-2}
                                              {:tag :not-it-3}])))
    (is (= nil (find-in-list "not-there" [{:tag :not-it-1}
                                          {:tag :kewl}
                                          {:tag :not-it-2}
                                          {:tag :not-it-3}]))))
  (testing "unwrap-if-needed"
    (is (= nil (unwrap-if-needed nil)))
    (is (= [] (unwrap-if-needed [])))
    (is (= {:tag :kewl} (unwrap-if-needed {:content {:tag :kewl}}))))
  (testing "path-find" 
    (is (= nil (path-find ["not-there"] [{:tag :data}])))
    (is (= [{:kewl :stuff}] (path-find ["data"] [{:tag :data :content [{:kewl :stuff}]}])))))
