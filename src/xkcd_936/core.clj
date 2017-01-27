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
    ["-min" "--minimum Minimum" "Minimum number of Characters"
     :default 4
     :parse-fn #(Integer/parseInt %)
     :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
    ["-max" "--maximum Maximum" "Maximum number of Characters"
     :default 7
     :parse-fn #(Integer/parseInt %)
     :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
    ["-h" "--help"]
  ])

(defn exit [status message]
  (println message)
  (System/exit status))

(defn secureShuffle [col]
  (let [al (ArrayList. col)]
    (Collections/shuffle al (SecureRandom.))
    (vec al)))

(defn lines [file]
  (with-open [reader (io/reader file)]
    (doall (line-seq reader))))

(def fileName "words.txt")

(def url "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english-usa-no-swears-short.txt")

(defn password [words options]
  (string/join (:delimiter options) (take (:count options) (secureShuffle words))))

(if-not (.exists (io/file fileName))
  (spit fileName (slurp url)))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 summary)
      errors (exit 1 errors))
    (println (password (lines fileName) options))
    ))
