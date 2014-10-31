(ns instaparse-presentation.letter-parser
  (:require [instaparse.core :as insta]))

(defn parse-letters 
  "Parses a string of consecutive digits into possible groupings of
   numbers from 1-26. Returns the result as a list of possible result
   strings, with the numbers 1-26 mapped to the letters a-z."
  [number-string]
  (->> number-string
       (insta/parses (insta/parser 
                       "string = letter+
                        letter = #'2[0-6]' | #'1[0-9]' | #'[1-9]'"))
       (map #(insta/transform
              {:letter (zipmap (map str (iterate inc 1)) 
                               (map char (range 97 123)))
               :string str} %))))
