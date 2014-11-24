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

(defn over? [game]
  (let [board (:board game)]
    (or (board/has-winner? board)
        (board/draw? board))))

(defn- display-game-result [game]
  (let [board (:board game)
        last-player (last (:players game))]
    (cond
      (board/has-winner? board) (io/show-winner last-player)
      (board/draw? board) (io/show-draw))))

(defn- board-with-move [board current-player]
  (let [mark (:mark current-player)
        move (move-of current-player)]
    (board/place-move mark move board)))

(defn- play-next-round [game current-player]
  (io/show-board (:board game))
  {:players (reverse (:players game))
   :board   (board-with-move (:board game) current-player)})

(defn play-round [game]
  (let [current-player (first (:players game))]
    (play-next-round game current-player)))

(defn play [game]
  (if (over? game)
    (display-game-result game)
    (recur (play-round game))))

