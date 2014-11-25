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
               (place-move computer [1 2 3 4 5 6 7 8 9]))))

  (it "blocks"
    (let [computer (new-computer-player \o)]
      (should= [\o \x \x 4 5 6 7 8 9]
               (place-move computer [1 \x \x 4 5 6 7 8 9]))))

  (it "blocks in rows"
    (should= 1 (best-move-for \o [1 \x \x 4 5 6 7 8 9]))
    (should= 2 (best-move-for \o [\x 2 \x 4 5 6 7 8 9]))
    (should= 3 (best-move-for \o [\x \x 3 4 5 6 7 8 9]))
    (should= 4 (best-move-for \o [1 2 3 4 \x \x 7 8 9]))
    (should= 5 (best-move-for \o [1 2 3 \x 5 \x 7 8 9]))
    (should= 6 (best-move-for \o [1 2 3 \x \x 6 7 8 9]))
    (should= 7 (best-move-for \o [1 2 3 4 5 6 7 \x \x]))
    (should= 8 (best-move-for \o [1 2 3 4 5 6 \x 8 \x]))
    (should= 9 (best-move-for \o [1 2 3 4 5 6 \x \x 9])))

  (it "blocks in columns"
    (should= 1 (best-move-for \o [1 2 3 \x 5 6 \x 8 9]))
    (should= 2 (best-move-for \o [1 2 3 4 \x 6 7 \x 9]))
    (should= 3 (best-move-for \o [1 2 3 4 5 \x 7 8 \x]))
    (should= 4 (best-move-for \o [\x 2 3 4 5 6 \x 8 9]))
    (should= 5 (best-move-for \o [1 \x 3 4 5 6 7 \x 9]))
    (should= 6 (best-move-for \o [1 2 \x 4 5 6 7 8 \x]))
    (should= 7 (best-move-for \o [\x 2 3 \x 5 6 7 8 9]))
    (should= 8 (best-move-for \o [1 \x 3 4 \x 6 7 8 9]))
    (should= 9 (best-move-for \o [1 2 \x 4 5 \x 7 8 9])))

  (it "blocks in diagonals"
    (should= 1 (best-move-for \o [1 2 3 4 \x 6 7 8 \x]))
    (should= 5 (best-move-for \o [\x 2 3 4 5 6 7 8 \x]))
    (should= 9 (best-move-for \o [\x 2 3 4 \x 6 7 8 9]))
    (should= 3 (best-move-for \o [1 2 3 4 \x 6 \x 8 9]))
    (should= 5 (best-move-for \o [1 2 \x 4 5 6 \x 8 9]))
    (should= 7 (best-move-for \o [1 2 \x 4 \x 6 7 8 9])))

  (it "wins in rows"
    (should= 1 (best-move-for \x [1 \x \x 4 5 6 7 8 9]))
    (should= 2 (best-move-for \x [\x 2 \x 4 5 6 7 8 9]))
    (should= 3 (best-move-for \x [\x \x 3 4 5 6 7 8 9]))
    (should= 4 (best-move-for \x [1 2 3 4 \x \x 7 8 9]))
    (should= 5 (best-move-for \x [1 2 3 \x 5 \x 7 8 9]))
    (should= 6 (best-move-for \x [1 2 3 \x \x 6 7 8 9]))
    (should= 7 (best-move-for \x [1 2 3 4 5 6 7 \x \x]))
    (should= 8 (best-move-for \x [1 2 3 4 5 6 \x 8 \x]))
    (should= 9 (best-move-for \x [1 2 3 4 5 6 \x \x 9])))

  (it "wins in columns"
    (should= 1 (best-move-for \x [1 2 3 \x 5 6 \x 8 9]))
    (should= 2 (best-move-for \x [1 2 3 4 \x 6 7 \x 9]))
    (should= 3 (best-move-for \x [1 2 3 4 5 \x 7 8 \x]))
    (should= 4 (best-move-for \x [\x 2 3 4 5 6 \x 8 9]))
    (should= 5 (best-move-for \x [1 \x 3 4 5 6 7 \x 9]))
    (should= 6 (best-move-for \x [1 2 \x 4 5 6 7 8 \x]))
    (should= 7 (best-move-for \x [\x 2 3 \x 5 6 7 8 9]))
    (should= 8 (best-move-for \x [1 \x 3 4 \x 6 7 8 9]))
    (should= 9 (best-move-for \x [1 2 \x 4 5 \x 7 8 9])))

  (it "wins in diagonals"
    (should= 1 (best-move-for \x [1 2 3 4 \x 6 7 8 \x]))
    (should= 5 (best-move-for \x [\x 2 3 4 5 6 7 8 \x]))
    (should= 9 (best-move-for \x [\x 2 3 4 \x 6 7 8 9]))
    (should= 3 (best-move-for \x [1 2 3 4 \x 6 \x 8 9]))
    (should= 5 (best-move-for \x [1 2 \x 4 5 6 \x 8 9]))
    (should= 7 (best-move-for \x [1 2 \x 4 \x 6 7 8 9]))))

