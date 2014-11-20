(ns tictactoe.player)

(defn new-player [mark]
  {:mark mark})

(defn mark [player]
  (:mark player))

(defn next-move [player]
  (read-line))
