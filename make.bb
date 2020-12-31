#!/usr/bin/env bb

(require '[clojure.string :as string])

(filter #(= \, %) "arst,")

(def moons ["🌑""🌒""🌓""🌔""🌕""🌖""🌗""🌘"])

(spit
 "readme.org"
 (reduce
  (fn [state link]
    (string/replace-first state "," (format "[[%s][%s]]"
                                            link
                                            (nth (reverse moons)
                                                 (mod
                                                  (count (filter #(= \, %) state))
                                                  (count moons)
                                                  )))))
  (slurp "readme_pre.txt")
  (-> "links.txt"
      slurp
      (string/split #"\n")
      shuffle)))
