(ns tictactoe.io
  (:require [clojure.string :as string]
            [tictactoe.board :as tttboard]))

(defn- format-line [line]
  (string/join " | " line))

(defn show-board [board]
  (doseq [row (partition (tttboard/width board) board)]
    (println (format-line row))))

(defn show-request-for-move [next-mark]
  (println (str "Next move for " next-mark ":")))

(defn show-winner [mark]
  (println (str "Winner is: " mark)))

(defn show-draw []
  (println "Game ended in a draw."))

