(ns tictactoe.io_spec
  (:require [speclj.core :refer :all]
            [tictactoe.io :refer :all]))

(describe "I/O"
  (it "prints the board"
    (let [output (with-out-str (show-board [1 2 3 4 5 6 7 8 9]))]
      (should= "1 | 2 | 3\n4 | 5 | 6\n7 | 8 | 9\n"
               output)))

  (it "shows a message for a winner"
    (let [output (with-out-str (show-winner \x))]
      (should= "Winner is: x\n"
               output)))

  (it "shows a message for a draw"
    (let [output (with-out-str (show-draw))]
      (should= "Game ended in a draw.\n"
               output)))

  (it "shows a message for an invalid move"
    (let [output (with-out-str (show-invalid-move))]
      (should= "Invalid move.\n"
               output)))

  (it "shows a request message when asking for a move"
    (with-in-str "1"
      (let [output (with-out-str (get-next-move \x []))]
        (should= "Next move for x:\n"
                 output))))

  (it "provides its move from $stdin"
    (with-out-str
      (should= 42
               (with-in-str "42"
                 (get-next-move \x [])))))

  (it "returns 0 for non-number input"
    (with-out-str
      (with-in-str "somerandomtext"
        (should= 0
                 (get-next-move \x nil))))))

