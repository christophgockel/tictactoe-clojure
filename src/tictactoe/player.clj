(ns tictactoe.player)

(def O \o)
(def X \x)

(defn new-player [mark next-move-callback]
  {:mark mark
   :next-move-callback next-move-callback})

(defn mark [player]
  (:mark player))

(defn opponent-of [mark]
  (if (= mark X) O X))

(defn- parse-int [value]
  (let [number-match (re-find #"\d+" value)]
    (if number-match
      (Integer/parseInt number-match)
      0)))

(defn next-move [player board]
  ((:next-move-callback player) (mark player) board))

