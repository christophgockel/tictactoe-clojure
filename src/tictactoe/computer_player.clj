(ns tictactoe.computer_player
  (:require [tictactoe.board :as board :only [place-move]]))

(defn mark [player]
  (:mark player))

(defn place-move [player board]
  (board/place-move (mark player) 1 board))

(defn new-computer-player [mark]
  {:mark mark})

