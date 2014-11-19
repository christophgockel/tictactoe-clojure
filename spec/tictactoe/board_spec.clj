(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "Board"
  (it "creates an empty board"
    (should= 9 (count (new-board 3)))))
