(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]))

(describe "Player"
  (with-stubs)

  (it "has a mark"
    (should= \x (mark (new-player \x nil))))

  (it "calls passed function for next-move"
    (let [callback (stub :callback)
          board [1 2 3 4 5 6 7 8 9]
          player (new-player \x callback)]
      (next-move player board)
      (should-have-invoked :callback {:with [\x board]}))))

