(ns tictactoe.computer_player
  (:require [tictactoe.board :as board :only [place-move winner]]
            [tictactoe.player :as player :only [X O opponent-of mark]]))

(defn place-move [player board]
  (board/place-move (player/mark player) 1 board))

(defn new-computer-player [mark]
  {:mark mark})

(defn- is-rateable? [board]
  (or (board/has-winner? board)
      (board/draw? board)))

(defn- rate [board mark]
  (let [move-count (- (count board) (count (board/free-positions board)))
        score (/ 1.0 move-count)]
    (cond
      (board/draw? board) 0
      (board/has-winner? board) (* score (if (= (board/winner board) mark) 1 -1)))
    ))

(declare negamax negamax-value)

(defn- board-scores [board mark]
  (let [available-moves (board/free-positions board)
        new-boards (map #(board/place-move mark %1 board) available-moves)]
    (map #(- (negamax %1 (player/opponent-of mark))) new-boards)))

(defn- negamax-value [board mark]
  (if (is-rateable? board)
    (rate board mark)
    (apply max (board-scores board mark))))

(def negamax (memoize negamax-value))

(defn best-move-for [mark board]
  (let [available-moves (board/free-positions board)
        scores (board-scores board mark)
        best-value (negamax board mark)
        best-move (.indexOf scores best-value)]
    (nth available-moves best-move)))

