(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.board :refer :all]
            [tictactoe.io :refer :all]))

(defn fake-player [mark]
  {:mark mark})

(describe "Game"
  (with-stubs)
  (with board    (new-board 3))
  (with player_a (fake-player "x"))
  (with player_b (fake-player "o"))
  (with game (new-game @player_a @player_b @board))
  (with show-board-stub (stub :show-board))

  (around [it]
    (with-redefs [tictactoe.io/show-board @show-board-stub]
      (with-in-str "1" (it))))

  (it "plays a round"
    (let [another-game (play-round @game)]
      (should= ["x" 2 3 4 5 6 7 8 9]
               (:board another-game))))

  (it "switches players after playing a round"
    (let [another-game (play-round @game)]
      (should= [@player_b @player_a]
               (:players another-game))))

  (it "displays the board before each round"
    (let [other-game (play-round @game)]
        (should-have-invoked :show-board))))

