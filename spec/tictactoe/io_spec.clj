(ns tictactoe.io_spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer [X]]
            [tictactoe.io :refer :all]))

(describe "I/O"
  (it "prints the board"
    (let [output (with-out-str (show-board [1 2 3 4 5 6 7 8 9]))]
      (should= "1 | 2 | 3\n4 | 5 | 6\n7 | 8 | 9\n\n"
               output)))

  (it "shows a message for a winner"
    (let [output (with-out-str (show-winner X))]
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
      (let [output (with-out-str (get-next-move X []))]
        (should= "Next move for x:\n"
                 output))))

  (it "provides its move from $stdin"
    (with-out-str
      (should= 42
               (with-in-str "42"
                 (get-next-move X [])))))

  (it "returns 0 for non-number input"
    (with-out-str
      (with-in-str "somerandomtext"
        (should= 0
                 (get-next-move X nil)))))

  (it "prompts for the game-mode"
    (should= 3
             (with-in-str "3\n" (get-game-mode))))

  (it "shows a message asking for a game-mode"
    (let [output (with-out-str (show-game-mode-prompt))]
      (should= "Chose Game Mode:\n"
               output)))

  (it "shows list of available game-modes"
    (let [options {:1 {:description "Human vs. Human"}
                   :2 {:description "Human vs. Computer"}}
          output (with-out-str (show-game-mode-options options))]
      (should= "1. Human vs. Human\n2. Human vs. Computer\n" output))))

