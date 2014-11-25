(ns tictactoe.computer_player_spec
  (:require [speclj.core :refer :all]
            [tictactoe.computer_player :refer :all]
            [tictactoe.player :refer :all]))

(describe "Computer Player"
  (it "is a thing"
    (should (new-computer-player O)))

  (it "has a mark"
    (should= X (mark (new-computer-player X))))

  (it "picks a move"
    (let [computer (new-computer-player O)]
      (should= [O 2 3 4 5 6 7 8 9]
               (place-move computer [1 2 3 4 5 6 7 8 9]))))

  (it "blocks"
    (let [computer (new-computer-player O)]
      (should= [O X X 4 5 6 7 8 9]
               (place-move computer [1 X X 4 5 6 7 8 9]))))

  (it "blocks in rows"
    (should= 1 (best-move-for O [1 X X 4 5 6 7 8 9]))
    (should= 2 (best-move-for O [X 2 X 4 5 6 7 8 9]))
    (should= 3 (best-move-for O [X X 3 4 5 6 7 8 9]))
    (should= 4 (best-move-for O [1 2 3 4 X X 7 8 9]))
    (should= 5 (best-move-for O [1 2 3 X 5 X 7 8 9]))
    (should= 6 (best-move-for O [1 2 3 X X 6 7 8 9]))
    (should= 7 (best-move-for O [1 2 3 4 5 6 7 X X]))
    (should= 8 (best-move-for O [1 2 3 4 5 6 X 8 X]))
    (should= 9 (best-move-for O [1 2 3 4 5 6 X X 9])))

  (it "blocks in columns"
    (should= 1 (best-move-for O [1 2 3 X 5 6 X 8 9]))
    (should= 2 (best-move-for O [1 2 3 4 X 6 7 X 9]))
    (should= 3 (best-move-for O [1 2 3 4 5 X 7 8 X]))
    (should= 4 (best-move-for O [X 2 3 4 5 6 X 8 9]))
    (should= 5 (best-move-for O [1 X 3 4 5 6 7 X 9]))
    (should= 6 (best-move-for O [1 2 X 4 5 6 7 8 X]))
    (should= 7 (best-move-for O [X 2 3 X 5 6 7 8 9]))
    (should= 8 (best-move-for O [1 X 3 4 X 6 7 8 9]))
    (should= 9 (best-move-for O [1 2 X 4 5 X 7 8 9])))

  (it "blocks in diagonals"
    (should= 1 (best-move-for O [1 2 3 4 X 6 7 8 X]))
    (should= 5 (best-move-for O [X 2 3 4 5 6 7 8 X]))
    (should= 9 (best-move-for O [X 2 3 4 X 6 7 8 9]))
    (should= 3 (best-move-for O [1 2 3 4 X 6 X 8 9]))
    (should= 5 (best-move-for O [1 2 X 4 5 6 X 8 9]))
    (should= 7 (best-move-for O [1 2 X 4 X 6 7 8 9])))

  (it "wins in rows"
    (should= 1 (best-move-for X [1 X X 4 5 6 7 8 9]))
    (should= 2 (best-move-for X [X 2 X 4 5 6 7 8 9]))
    (should= 3 (best-move-for X [X X 3 4 5 6 7 8 9]))
    (should= 4 (best-move-for X [1 2 3 4 X X 7 8 9]))
    (should= 5 (best-move-for X [1 2 3 X 5 X 7 8 9]))
    (should= 6 (best-move-for X [1 2 3 X X 6 7 8 9]))
    (should= 7 (best-move-for X [1 2 3 4 5 6 7 X X]))
    (should= 8 (best-move-for X [1 2 3 4 5 6 X 8 X]))
    (should= 9 (best-move-for X [1 2 3 4 5 6 X X 9])))

  (it "wins in columns"
    (should= 1 (best-move-for X [1 2 3 X 5 6 X 8 9]))
    (should= 2 (best-move-for X [1 2 3 4 X 6 7 X 9]))
    (should= 3 (best-move-for X [1 2 3 4 5 X 7 8 X]))
    (should= 4 (best-move-for X [X 2 3 4 5 6 X 8 9]))
    (should= 5 (best-move-for X [1 X 3 4 5 6 7 X 9]))
    (should= 6 (best-move-for X [1 2 X 4 5 6 7 8 X]))
    (should= 7 (best-move-for X [X 2 3 X 5 6 7 8 9]))
    (should= 8 (best-move-for X [1 X 3 4 X 6 7 8 9]))
    (should= 9 (best-move-for X [1 2 X 4 5 X 7 8 9])))

  (it "wins in diagonals"
    (should= 1 (best-move-for X [1 2 3 4 X 6 7 8 X]))
    (should= 5 (best-move-for X [X 2 3 4 5 6 7 8 X]))
    (should= 9 (best-move-for X [X 2 3 4 X 6 7 8 9]))
    (should= 3 (best-move-for X [1 2 3 4 X 6 X 8 9]))
    (should= 5 (best-move-for X [1 2 X 4 5 6 X 8 9]))
    (should= 7 (best-move-for X [1 2 X 4 X 6 7 8 9]))))

