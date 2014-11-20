(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]))

(describe "Player"
  (it "has a mark"
    (should= "x" (mark (new-player "x"))))

  (it "provides its move from $stdin"
    (should= 42
      (with-in-str "42"
        (next-move (new-player "x")))))

  (it "returns 0 for non-number input"
    (with-in-str "somerandomtext"
      (should= 0
               (next-move (new-player "x"))))))

