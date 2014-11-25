(ns tictactoe.board_spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer [X O]]
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
      (should= [X 2 3 4 5 6 7 8 9]
               (place-move X 1 (new-board 3)))))

  (context "is-valid-move?"
    (with board (place-move X 1 (new-board 3)))

    (it "returns true on valid moves"
      (should= true (is-valid-move? 2 @board)))

    (it "returns false on invalid moves"
      (should= false (is-valid-move? 1 @board)))

    (it "returns false on non-number positions"
      (let [the-board (place-move X 1 (new-board 3))]
        (should= false (is-valid-move? "wat" the-board))))

    (it "returns false on out-of-bounds indexes"
      (let [the-board (place-move X 1 (new-board 3))]
        (should= false (is-valid-move? 42 the-board)))))

  (context "winning combinations"
    (it "identifies winning rows"
      (should= true (has-winner? [X X X 4 5 6 7 8 9]))
      (should= true (has-winner? [1 2 3 X X X 7 8 9]))
      (should= true (has-winner? [1 2 3 4 5 6 X X X])))

    (it "identifies winning columns"
      (should= true (has-winner? [X 2 3 X 5 6 X 8 9]))
      (should= true (has-winner? [1 X 3 4 X 6 7 X 9]))
      (should= true (has-winner? [1 2 X 4 5 X 7 8 X])))

    (it "identifies winning diagonals"
      (should= true (has-winner? [X 2 3 4 X 6 7 8 X]))
      (should= true (has-winner? [1 2 X 4 X 6 X 8 9])))

    (it "identifies the winner"
      (should= X (winner [X X X 4 5 6 7 8 9]))
      (should= O (winner [1 2 O 4 O 6 O 8 9]))))

  (context "free-positions"
    (it "contains all positions on an empty board"
      (should= [1 2 3 4 5 6 7 8 9]
               (free-positions (new-board 3))))

    (it "returns free positions on an ongoing board"
      (should= [1 9]
               (free-positions [1 X X O O X X X 9])))

    (it "returns empty list on a finished board"
      (should= []
               (free-positions [O X X O O X X X O]))))

  (context "draw?"
    (it "returns true on draws"
      (should= true (draw? [X O X O O X O X O])))

    (it "returns false when there is no draw"
      (should= false (draw? [X X X O O X O X O])))))

