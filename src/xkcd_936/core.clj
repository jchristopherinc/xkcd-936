(ns xkcd-936.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as string]
            [clojure.java.io :as io])
  (:import [java.security SecureRandom]
           [java.util ArrayList Collections])
  (:gen-class))

(def cli-options
  [
    ["-s" "--suggestions Suggestions" "Number of Suggestions"
     :default 1
     :parse-fn #(Integer/parseInt %)
     :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
    ["-d" "--delimiter Delimiter" "Delimiter"
     :default \space]
    ["-c" "--count Count" "Number of words per suggestion"
     :default 4
     :parse-fn #(Integer/parseInt %)
     :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
    ["-l" "--lte Minimum" "Less than or Equal to x number of Characters"
     :default 4
     :parse-fn #(Integer/parseInt %)
     :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
    ["-g" "--gte Maximum" "Greater than or Equal to x number of Characters"
     :default 7
     :parse-fn #(Integer/parseInt %)
     :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
    ["-h" "--help"]
  ])

(def fileName "words.txt")

(def url "https://raw.githubusercontent.com/jchristopherinc/xkcd-936/master/words.txt")

(if-not (.exists (io/file fileName))
  (spit fileName (slurp url)))

(defn lines [file]
  (with-open [reader (io/reader file)]
    (doall (line-seq reader))))

(defn secureShuffle [col]
  (let [al (ArrayList. col)]
    (Collections/shuffle al (SecureRandom.))
    (vec al)))

(defn randomWordFromOptions [words options]
  (let [randomWord (string/join \space (take 1 (secureShuffle words)))]
    (cond
      (and (>= (count randomWord) (:lte options)) (<= (count randomWord) (:gte options))) randomWord
      :else (randomWordFromOptions words options)
      )))

(defn password [words options]
  (let [al (ArrayList.)]
    (loop [i 0]
      (when (< i (:count options))
        (.add al (randomWordFromOptions words options))
        (recur (inc i))))
  (string/join (:delimiter options) al)))

(defn exit [status message]
  (println message)
  (System/exit status))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 summary)
      errors (exit 1 errors))
    (let [wordList (lines fileName)]
      (loop [i 0]
        (when (< i (:suggestions options))
          (println (password wordList options))
          (recur (inc i)))))))
