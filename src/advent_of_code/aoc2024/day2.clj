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

(defn diffs [d] (map - (next d) d))

(defn safe-increasing? [d]
  (let [d (diffs d) ]
    (every? (fn [x] (<= 1 x 3)) d)))
(defn safe-decreasing? [d]
  (let [d (diffs d) ]
    (every? (fn [x] (<= -3 x -1)) d)))

(defn safe? [d] (or (safe-decreasing? d)
                    (safe-increasing? d)))

(println "Part 1")
(defn part1 [d] (count (filter safe? d)))
(println (part1 test-data-ints))
(println (part1 data-ints))

(println "\nPart 2")

(defn without [d n]
  "Given a list a, return the list without the item at index n"
  (concat (take n d) (drop (+ n 1) d)))

(defn without-one [d]
  "given a list of values return all possible lists with one item removed"
  (map (fn [n] (without d n)) (range 0 (count d)))
  )

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn safe-damped? [d] (in? (map safe? (without-one d)) true))
(defn part2 [d] (count (filter safe-damped? d)))

(println (part2 test-data-ints))
(println (part2 data-ints))