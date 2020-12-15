#!/usr/bin/env bb

(require '[clojure.string :as string])

(spit
 "readme.org"
 (reduce
  (fn [state link]
    (string/replace-first state "," (format "[[%s][🎄]]" link)))
  (slurp "readme_pre.txt")
  (-> "links.txt"
      slurp
      (string/split #"\n")
      shuffle)))
