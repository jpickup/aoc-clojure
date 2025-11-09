(ns advent-of-code.aoc2024.day1
  (:require [clojure.string :as str])
  )
(def start-time (System/currentTimeMillis))

(def test-data (slurp "resources/2024/Day1/Day1-test.txt"))
(def test-data-lines (str/split-lines test-data))
(def data (slurp "resources/2024/Day1/Day1.txt"))
(def data-lines (str/split-lines data))

(defn split-by-space-as-int [s]  (map (fn [s] (Integer/parseInt s)) (str/split s #" +")))
(defn left [a] (map first (map split-by-space-as-int a)))
(defn right [a] (map first (map rest (map split-by-space-as-int a))))

(defn sorted-pairs [d] (map vector (sort (left d)) (sort (right d)) ))
(defn sum [x] (reduce + x))

(println "Part 1")
(defn dist [v] (let [[l r] v] (abs (- l r))))
(defn part1 [d] (sum (map dist (sorted-pairs d))))
(println (part1 test-data-lines))
(println (part1 data-lines))

(println "\nPart 2")
(defn matches [v l] (filter (fn [n] (= n v)) l))
(defn sum-matches [v l] (sum (matches v l)))
(defn part2 [d] (sum (map (fn [v] (sum-matches v (right d))) (left d))))
(println (part2 test-data-lines))
(println (part2 data-lines))

(def end-time (System/currentTimeMillis))

(println (- end-time start-time) "ms")