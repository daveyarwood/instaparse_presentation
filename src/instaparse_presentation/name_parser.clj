(ns ^{:doc "Parses names into first, middle and last."}
  instaparse-presentation.name-parser
  (:require [instaparse.core :as insta]))

(def name-parser
  (insta/parser
    "full-name = first <space> (middle <space>)* last
     first     = name | initial
     middle    = name | initial
     last      = name | initial
     name      = #'[A-Za-z-\\'\"]+'
     initial   = #'[A-Za-z]' '.'
     space     = #'\\s+'"))

(def funkify
  (partial insta/transform
    {:name      str
     :initial   str 
     :last      clojure.string/upper-case
     :middle    #(clojure.string/reverse (apply str %&))
     :first     count
     :full-name #(clojure.string/join " " %&)}))
