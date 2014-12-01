(ns tictactoe.cli_runner_spec
  (:require [speclj.core :refer :all]
            [tictactoe.io :refer :all :as io]
            [tictactoe.game :refer :all :as game :only [play]]
            [tictactoe.player :refer :all :only [X O new-player]]
            [tictactoe.cli_runner :refer :all]))

(describe "CLI Runner"
  (with-stubs)
  (with show-game-mode-prompt-stub (stub :game-mode-prompt))
  (with show-game-mode-options-stub (stub :game-mode-options))
  (with get-next-move-stub (stub :get-next-move {:return 1}))
  (with play-stub (stub :play))

  (around [it]
    (with-redefs [io/show-game-mode-prompt @show-game-mode-prompt-stub
                  io/show-game-mode-options @show-game-mode-options-stub
                  io/get-next-move @get-next-move-stub
                  game/play @play-stub]
      (it)))

  (context "initialize"
    (it "shows game mode options"
      (let [options {:1 {:players {:a (new-player X @get-next-move-stub)
                                   :b (new-player O @get-next-move-stub)}}}]
        (with-in-str "1" (initialize-and-play options))
        (should-have-invoked :game-mode-options {:with [options]})))

    (it "asks for the game mode"
      (let [options {:1 {:a {} :b {}}}]
        (with-in-str "1" (initialize-and-play options))
        (should-have-invoked :game-mode-prompt)))

    (it "keeps asking for a valid choice"
      (let [options {:1 {:a {} :b {}}}]
        (with-in-str "3\n2\n1" (initialize-and-play options))
        (should-have-invoked :game-mode-prompt {:times 3})))))

