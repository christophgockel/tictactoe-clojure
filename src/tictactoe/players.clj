(ns tictactoe.players
  (:require [tictactoe.player :refer [new-player X O]]
            [tictactoe.negamax :refer [best-move-for] :as ai]
            [tictactoe.io :as io]))

(defn available-player-types []
  (sorted-map
    :1 {:description "Human vs. Human"
        :players {:a (new-player X io/get-next-move)
                  :b (new-player O io/get-next-move)}}
    :2 {:description "Human vs. Computer"
        :players {:a (new-player X io/get-next-move)
                  :b (new-player O ai/best-move-for)}}
    :3 {:description "Computer vs. Human"
        :players {:a (new-player X ai/best-move-for)
                  :b (new-player O io/get-next-move)}}
    :4 {:description "Computer vs. Computer"
        :players {:a (new-player X ai/best-move-for)
                  :b (new-player O ai/best-move-for)}}))

