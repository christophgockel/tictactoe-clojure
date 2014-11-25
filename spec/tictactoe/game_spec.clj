(ns tictactoe.game_spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.board :refer :all]
            [tictactoe.io :refer :all :as io]))

(describe "Game"
  (with-stubs)
  (with show-board-stub (stub :show-board))
  (with show-winner-stub (stub :show-winner))
  (with show-draw-stub (stub :show-draw))
  (with show-invalid-move-stub (stub :show-invalid-move))
  (with get-next-move-stub (stub :get-next-move {:return 1}))

  (with board    (new-board 3))
  (with player_a (new-player \x @get-next-move-stub))
  (with player_b (new-player \o @get-next-move-stub))
  (with game (new-game @player_a @player_b @board))

  (around [it]
    (with-redefs [io/show-board @show-board-stub
                  io/get-next-move @get-next-move-stub
                  io/show-winner @show-winner-stub
                  io/show-draw @show-draw-stub
                  io/show-invalid-move @show-invalid-move-stub]
      (it)))

  (it "plays a round"
    (let [another-game (play-round @game)]
      (should= [\x 2 3 4 5 6 7 8 9]
               (:board another-game))))

  (it "switches players after playing a round"
    (let [another-game (play-round @game)]
      (should= [@player_b @player_a]
               (:players another-game))))

  (it "displays the board before each round"
    (let [other-game (play-round @game)]
        (should-have-invoked :show-board)))

  (it "asks for the next move per round"
    (let [other-game (play-round @game)]
      (should-have-invoked :get-next-move {:with [\x @board]})))

;  (it "displays the board and prompt in each round"
;    (let [other-game (play-round @game)]
;        (should-have-invoked :show-board)
;        (should-have-invoked :show-request-for-move)))

  (it "is over when there is a winner"
    (let [winning-board [\x \x \x 4 5 6 7 8 9]
          game (new-game @player_a @player_b winning-board)]
      (should= true
               (over? game))))

  (it "is over when there is a draw"
    (let [draw-board [\x \o \x \o \o \x \o \x \o]
          game (new-game @player_a @player_b draw-board)]
      (should= true
               (over? game))))

  (it "displays a winner message"
    (let [winning-board [\x \x \x 4 5 6 7 8 9]
          game (new-game @player_b @player_a winning-board)
          other-game (play game)]
        (should-have-invoked :show-winner {:with [\x]})))

  (it "displays a draw message"
    (let [draw-board [\x \o \x \o \o \x \o \x \o]
          game (new-game @player_a @player_b draw-board)
          other-game (play game)]
        (should-have-invoked :show-draw)))

  (it "plays rounds until it's over"
    (let [board [1 \x \x 4 5 6 7 8 9]
          game (new-game @player_a @player_b board)
          next-game (play game)]
      (should-have-invoked :show-winner)))

  (it "does not proceed with the next round on invalid moves"
    (let [board [\x \x \o 4 5 6 7 8 9]
          game (new-game @player_a @player_b board)
          next-game (play-round game)]
      (should= @player_a
               (first (:players next-game)))))

  (it "displays an invalid move message"
    (let [board [\x \x \o 4 5 6 7 8 9]
          game (new-game @player_a @player_b board)
          next-game (play-round game)]
      (should-have-invoked :show-invalid-move))))

