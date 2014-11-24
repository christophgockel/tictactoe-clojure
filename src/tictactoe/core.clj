(ns tictactoe.core
  (:gen-class)
  (:require [tictactoe.game :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.board :refer :all]
            [tictactoe.io :refer :all]))

(defn -main []
  (let [player_a (new-player \X)
        player_b (new-player \O)
        board    (new-board 3)
        game     (new-game player_a player_b board)]
    (play game)))
