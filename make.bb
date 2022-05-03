#!/usr/bin/env bb

(require '[clojure.string :as string])

(def orbs ["⚪" "🔵" "🟣" "🟢" "⚪"  "🟡" "🔴" "🟠" "⚪" ])

(spit
 "readme.md"
 (reduce
  (fn [state links]
    (reduce
     (fn [state-in [replace link]]
       (string/replace-first state-in
                             (re-pattern (format "([^a-zA-Z-])%s([^a-zA-Z])" (inc replace)))
                             (format "$1<a href=\"%s\">%s</a>$2"
                                     link
                                     (nth orbs (mod replace (count orbs))))))
     state (partition 2 (interleave (range 9) links))))
  (slurp "readme_pre.txt")
  (partition 9
             (-> "links.txt"
                 slurp
                 (string/split #"\n")
                 shuffle))))
