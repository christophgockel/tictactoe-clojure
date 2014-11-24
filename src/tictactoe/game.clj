(ns tictactoe.game
  (:require [tictactoe.player :as player]
            [tictactoe.board :as board]
            [tictactoe.io :as io]))

(defn- move-of [player]
  (io/show-request-for-move (:mark player))
  (player/next-move player))

(defn- display-game-result [game]
  (let [board (:board game)
        last-player (last (:players game))]
    (cond
      (board/has-winner? board) (io/show-winner last-player)
      (board/draw? board) (io/show-draw))))

(defn- board-with-move [board current-player]
  (let [mark (:mark current-player)
        move (move-of current-player)]
    (if (board/is-valid-move? move board)
      (board/place-move mark move board)
      board)))

(defn- play-next-round [game current-player]
  (let [old-board (:board game)
        new-board (board-with-move old-board current-player)]
    (if (= old-board new-board)
      (do
        (io/show-invalid-move)
        {:players (:players game)
         :board   old-board})
      {:players (reverse (:players game))
       :board   new-board})))

(defn play-round [game]
  (let [current-player (first (:players game))]
    (io/show-board (:board game))
    (play-next-round game current-player)))

(defn over? [game]
  (let [board (:board game)]
    (or (board/has-winner? board)
        (board/draw? board))))

(defn play [game]
  (if (over? game)
    (display-game-result game)
    (recur (play-round game))))

(defn new-game [player_a player_b board]
  {:players [player_a player_b]
   :board   board})

