(ns tictactoe.cli_runner
  (:require [tictactoe.player :as player]
            [tictactoe.board :refer :all :only [new-board]]
            [tictactoe.game :refer :all :only [new-game]]
            [tictactoe.io :as io]))

(defn- print-options [options]
  (io/show-game-mode-options options))

(defn- get-valid-game-mode [options]
    (io/show-game-mode-prompt)
  (let [choice (io/get-game-mode)
        keyword-choice (keyword (str choice))]
    (if (contains? options keyword-choice)
      (get options keyword-choice)
      (recur options))))

(defn- get-game-mode [options]
  (print-options options)
  (get-valid-game-mode options))

(defn initialize-and-play [player-types]
  (let [mode (get-game-mode player-types)
        players (:players mode)
        board (new-board 3)
        game (new-game (:a players) (:b players) board)]
    (play game)))

