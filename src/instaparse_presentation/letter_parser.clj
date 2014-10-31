(ns instaparse-presentation.letter-parser
  (:require [instaparse.core :as insta]))

(defn parse-letters [number-string]
  (->> number-string
       (insta/parses (insta/parser 
                       "string = letter+
                        letter = #'2[0-6]' | #'1[0-9]' | #'[1-9]'"))
       (map #(insta/transform
              {:letter (zipmap (map str (iterate inc 1)) 
                               (map char (range 97 123)))
               :string str} %))))
