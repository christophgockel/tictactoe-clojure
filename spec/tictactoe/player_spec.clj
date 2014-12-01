(ns tictactoe.player_spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]))

(describe "Player"
  (with-stubs)

  (it "has a mark"
    (should= X (mark (new-player X nil))))

  (it "calls passed function for next-move"
    (let [callback (stub :callback)
          board [1 2 3 4 5 6 7 8 9]
          player (new-player X callback)]
      (next-move player board)
      (should-have-invoked :callback {:with [X board]})))

  (it "knows the opponent mark"
    (should= O (opponent-of X))
    (should= X (opponent-of O))))

