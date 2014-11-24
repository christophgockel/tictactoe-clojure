(ns tictactoe.board_spec
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
    (with board (place-move "x" 1 (new-board 3)))

    (it "returns true on valid moves"
      (should= true (is-valid-move? 2 @board)))

    (it "returns false on invalid moves"
      (should= false (is-valid-move? 1 @board)))

    (it "returns false on non-number positions"
      (let [the-board (place-move \x 1 (new-board 3))]
        (should= false (is-valid-move? "wat" the-board))))

    (it "returns false on out-of-bounds indexes"
      (let [the-board (place-move \x 1 (new-board 3))]
        (should= false (is-valid-move? 42 the-board)))))

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
      (should= "o" (winner [1 2 "o" 4 "o" 6 "o" 8 9]))))

  (context "free-positions"
    (it "contains all positions on an empty board"
      (should= [1 2 3 4 5 6 7 8 9]
               (free-positions (new-board 3))))

    (it "returns free positions on an ongoing board"
      (should= [1 9]
               (free-positions [1 \x \x \o \o \x \x \x 9])))

    (it "returns empty list on a finished board"
      (should= []
               (free-positions [\o \x \x \o \o \x \x \x \o]))))

  (context "draw?"
    (it "returns true on draws"
      (should= true (draw? [\x \o \x \o \o \x \o \x \o])))

    (it "returns false when there is no draw"
      (should= false (draw? [\x \x \x \o \o \x \o \x \o])))))

