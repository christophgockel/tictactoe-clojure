(ns tictactoe.core
  (:gen-class)
  (:require [tictactoe.game :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.board :refer :all]
            [tictactoe.io :refer :all :as io]))

(defn -main []
  (let [player_a (new-player \X io/get-next-move)
        player_b (new-player \O io/get-next-move)
        board    (new-board 3)
        game     (new-game player_a player_b board)]
    (play game)))
