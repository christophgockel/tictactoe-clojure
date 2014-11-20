(ns tictactoe.player)

(defn new-player [mark]
  {:mark mark})

(defn mark [player]
  (:mark player))

(defn- parse-int [value]
  (let [number-match (re-find #"\d+" value)]
    (if number-match
      (Integer/parseInt number-match)
      0)))

(defn next-move [player]
  (parse-int (read-line)))

