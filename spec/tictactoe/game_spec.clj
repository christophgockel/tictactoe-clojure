(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.board :refer :all]))

(defn fake-player [mark]
  {:mark mark})

(describe "Game"
  (let [board (new-board 3)
        player_a (fake-player "x")
        player_b (fake-player "o")]

    (it "plays a round"
      (with-in-str "1"
        (let [game (new-game player_a player_b board)
              another-game (play-round game)]
          (should= ["x" 2 3 4 5 6 7 8 9]
                   (:board another-game)))))

    (it "switches players after playing a round"
      (with-in-str "1"
        (let [game (new-game player_a player_b board)
              another-game (play-round game)]
          (should= [player_b player_a]
                   (:players another-game)))))))

