#!/usr/bin/env bb

(require '[clojure.string :as string])

;; (def moons ["🌑""🌒""🌓""🌔""🌕""🌖""🌗""🌘"])

(def moons ["🟣" "🟢" "🟠" "🔴"])

(spit
 "readme.md"
 (reduce
  (fn [state links]
    (reduce
     (fn [state-in [replace link]]
       (string/replace-first state-in replace
                             (format "<a href=\"%s\">%s</a>"
                                     link
                                     (nth moons (dec (Integer/parseInt replace)))
                                     )))
     state (partition 2 (interleave '("1" "2" "3" "4") links))))
  (slurp "readme_pre.txt")
  (partition 4
             (-> "links.txt"
                 slurp
                 (string/split #"\n")
                 shuffle
                 ))))
