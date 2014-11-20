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
               (place-move "x" 1 (new-board 3)))))

  (context "is-valid-move?"
    (let [board (place-move "x" 1 (new-board 3))]
      (it "returns true on valid moves"
        (should (is-valid-move? 2 board)))

      (it "returns false on invalid moves"
        (should-not (is-valid-move? 1 board)))))

  (context "winning combinations"
    (it "identifies winning rows"
      (should= true (has-winner? ["x" "x" "x" 4 5 6 7 8 9]))
      (should= true (has-winner? [1 2 3 "x" "x" "x" 7 8 9]))
      (should= true (has-winner? [1 2 3 4 5 6 "x" "x" "x"])))

    (it "identifies winning columns"
      (should= true (has-winner? ["x" 2 3 "x" 5 6 "x" 8 9]))
      (should= true (has-winner? [1 "x" 3 4 "x" 6 7 "x" 9]))
      (should= true (has-winner? [1 2 "x" 4 5 "x" 7 8 "x"])))

    (it "identifies winning diagonals"
      (should= true (has-winner? ["x" 2 3 4 "x" 6 7 8 "x"]))
      (should= true (has-winner? [1 2 "x" 4 "x" 6 "x" 8 9])))

    (it "identifies the winner"
      (should= "x" (winner ["x" "x" "x" 4 5 6 7 8 9]))
      (should= "o" (winner [1 2 "o" 4 "o" 6 "o" 8 9])))))

