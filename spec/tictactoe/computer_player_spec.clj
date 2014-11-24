(ns tictactoe.computer_player_spec
  (:require [speclj.core :refer :all]
            [tictactoe.computer_player :refer :all]))

(describe "Computer Player"
  (it "is a thing"
    (should (new-computer-player \o)))

  (it "has a mark"
    (should= \x (mark (new-computer-player \x))))

  (it "picks a move"
    (let [computer (new-computer-player \o)]
      (should= [\o 2 3 4 5 6 7 8 9]
               (place-move computer [1 2 3 4 5 6 7 8 9])))))

