(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "Board"
  (context "new-board"
    (it "has given size"
      (should= 9 (count (new-board 3))))

    (it "contains free indexes"
      (should= [1 2 3 4 5 6 7 8 9]
               (new-board 3))))

  (context "place-move"
    (it "indexes are one based"
      (should= ["x" 2 3 4 5 6 7 8 9]
               (place-move "x" 1 (new-board 3))))))
