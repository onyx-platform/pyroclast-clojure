(ns pyroclast-clojure.v1.roaming.identifier-test
  (:require [clojure.test :refer :all]
            [pyroclast-clojure.v1.roaming.client :as roaming]
            [pyroclast-clojure.v1.roaming.identifier :as i]
            [pyroclast-clojure.v1.roaming.service :as s]
            [pyroclast-clojure.v1.roaming.topic :as t]))

(def config {:endpoint "http://localhost:10557"})

(deftest test-random-uuid
  (let [service (-> (s/new-service)
                    (t/input-topic "input")
                    (i/random-uuid "result")
                    (t/output-topic "output"))
        records [{}]
        simulation (roaming/simulate! config service records)]
    (is (:success? simulation))
    (is (not (nil? (get-in simulation [:result :output-records 0 "result"]))))))

(deftest test-hash
  (let [service (-> (s/new-service)
                    (t/input-topic "input")
                    (i/hash "result")
                    (t/output-topic "output"))
        records [{}]
        simulation (roaming/simulate! config service records)]
    (is (:success? simulation))
    (is (not (nil? (get-in simulation [:result :output-records 0 "result"]))))))
