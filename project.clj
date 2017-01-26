(defproject xkcd-936 "0.1.0-SNAPSHOT"
  :description "Generate XKCD Style Passwords"
  :url "https://github.com/jchristopherinc/xkcd-936E"
  :license {:name "The Unlicense"
            :url "http://unlicense.org/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.5"]]
  :main ^:skip-aot xkcd-936.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
