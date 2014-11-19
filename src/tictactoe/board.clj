(ns tictactoe.board)

(defn new-board [size]
  (into [] (for [x (range (* size size))] (+ x 1))))

(defn place-move [mark position board]
  (assoc board (- position 1) mark))
