(ns instaparse-presentation.unicode-parser
  (:require [instaparse.core :as insta]))

(defn parse-unicode-chars [number-string]
  (->> number-string
       (insta/parses (insta/parser "
         string         = character+
         character      = five-digits | four-digits | three-digits | two-digits
         <five-digits>  = #'6553[0-5]' | #'655[0-2][0-9]' | #'65[0-4][0-9]{2}' |
                          #'6[0-4][0-9]{3}' | #'[1-5][0-9]{4}'
         <four-digits>  = #'[1-9][0-9]{3}'
         <three-digits> = #'[1-9][0-9]{2}'
         <two-digits>   = #'[4-9][0-9]' | #'3[2-9]'"))
        (map #(insta/transform
                {:character (fn [s] (Integer/parseInt s))
                 :string    (fn [& chars] (apply str (map char chars)))} %))))
