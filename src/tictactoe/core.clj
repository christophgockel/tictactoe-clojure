(ns tictactoe.core
  (:gen-class)
  (:require [tictactoe.players :refer :all :as players]
            [tictactoe.cli_runner :refer :all :as runner]))

(defn -main []
  (runner/initialize-and-play (players/available-player-types)))

