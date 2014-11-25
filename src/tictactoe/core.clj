(ns tictactoe.core
  (:gen-class)
  (:require [tictactoe.game :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.computer_player :as ai :only [best-move-for]]
            [tictactoe.board :refer :all]
            [tictactoe.io :refer :all :as io]))

(defn -main []
  (let [player_a (new-player X ai/best-move-for);io/get-next-move)
        player_b (new-player O ai/best-move-for)
        board    (new-board 3)
        game     (new-game player_a player_b board)]
    (play game)))
