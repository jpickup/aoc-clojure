(ns advent-of-code.aoc2024.day2
  (:require [clojure.string :as str])
  )

(def test-data (slurp "resources/2024/Day2/Day2-test.txt"))
(def test-data-lines (str/split-lines test-data))
(def data (slurp "resources/2024/Day2/Day2.txt"))
(def data-lines (str/split-lines data))
(defn split-by-space-as-int [s]  (map (fn [s] (Integer/parseInt s)) (str/split s #" +")))

(def test-data-ints (map split-by-space-as-int test-data-lines))
(def data-ints (map split-by-space-as-int data-lines))

(defn diffs [a] (map - (next a) a))

(defn safe-increasing? [a]
  (let [d (diffs a) ]
    (every? (fn [x] (<= 1 x 3)) d)))
(defn safe-decreasing? [a]
  (let [d (diffs a) ]
    (every? (fn [x] (<= -3 x -1)) d)))

(defn safe? [a] (or (safe-decreasing? a)
                    (safe-increasing? a)))
(defn part1 [d] (count (filter safe? d)))

(println "Part 1")
(println (part1 test-data-ints))
(println (part1 data-ints))
(println "\nPart 2")