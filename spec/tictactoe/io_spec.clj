(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.io :refer :all]))

(describe "I/O"
  (it "prints the board"
    (let [output (with-out-str (show-board [1 2 3 4 5 6 7 8 9]))]
      (should= "1 | 2 | 3\n4 | 5 | 6\n7 | 8 | 9\n"
               output))))

