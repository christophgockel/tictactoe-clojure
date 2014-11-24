(ns tictactoe.io_spec
  (:require [speclj.core :refer :all]
            [tictactoe.io :refer :all]))

(describe "I/O"
  (it "prints the board"
    (let [output (with-out-str (show-board [1 2 3 4 5 6 7 8 9]))]
      (should= "1 | 2 | 3\n4 | 5 | 6\n7 | 8 | 9\n"
               output)))

  (it "shows message for requesting a move"
    (let [output (with-out-str (show-request-for-move \x))]
      (should= "Next move for x:\n"
               output)))

  (it "show a message for a winner"
    (let [output (with-out-str (show-winner \x))]
      (should= "Winner is: x\n"
               output)))

  (it "show a message for a draw"
    (let [output (with-out-str (show-draw))]
      (should= "Game ended in a draw.\n"
               output))))

