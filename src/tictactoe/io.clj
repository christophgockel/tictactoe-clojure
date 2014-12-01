(ns tictactoe.io
  (:require [clojure.string :as string]
            [tictactoe.board :as tttboard]))

(defn show-board [board]
  (let [format-line (fn [line] (string/join " | " line))]
    (doseq [row (partition (tttboard/width board) board)]
      (println (format-line row)))))

(defn show-winner [mark]
  (println (str "Winner is: " mark)))

(defn show-draw []
  (println "Game ended in a draw."))

(defn show-invalid-move []
  (println "Invalid move."))

(letfn [(parse-int [value]
          (let [number-match (re-find #"\d+" value)]
            (if number-match
              (Integer/parseInt number-match)
              0)))]
  (defn get-next-move [mark board]
    (println (str "Next move for " mark ":"))
    (parse-int (read-line)))

  (defn get-game-mode []
    (parse-int (read-line))))

(defn show-game-mode-prompt []
  (println "Chose Game Mode:"))

(defn show-game-mode-options [player-types]
  (let [indices (map #(name %) (keys player-types))
        values (vals player-types)]
    (doseq [index indices]
      (println (str index ". " (:description ((keyword index) player-types)))))))

