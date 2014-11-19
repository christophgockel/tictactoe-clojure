(ns tictactoe.board)

(defn new-board [size]
  (vec (repeat (* size size) nil)))
