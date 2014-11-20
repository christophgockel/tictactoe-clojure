(ns tictactoe.game
  (:require [tictactoe.player :as player]
            [tictactoe.board :as board]))

(defn new-game [player_a player_b board]
  {:players [player_a player_b]
   :board   board})

(defn play-round [game]
  (let [current-player (first (:players game))
        the-move (player/next-move current-player)
        new-board (board/place-move (:mark current-player) the-move (:board game))]
    {:players (reverse (:players game))
     :board new-board}))

