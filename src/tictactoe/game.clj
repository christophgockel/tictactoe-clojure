(ns tictactoe.game
  (:require [tictactoe.player :as player]
            [tictactoe.board :as board]
            [tictactoe.io :as io]))

(defn new-game [player_a player_b board]
  {:players [player_a player_b]
   :board   board})

(defn- move-of [player]
  (io/show-request-for-move (:mark player))
  (player/next-move player))

(defn play-round [game]
  (let [current-player (first (:players game))]
    (do
      (io/show-board (:board game))
      {:players (reverse (:players game))
       :board (board/place-move
                (:mark current-player)
                (move-of current-player)
                (:board game)) })))

(defn over? [game]
  (let [board (:board game)]
    (or (board/has-winner? board)
        (board/draw? board))))

