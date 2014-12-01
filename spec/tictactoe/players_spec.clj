(ns tictactoe.players-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]
            [tictactoe.players :refer :all]))

(describe "Players"
  (context "available-player-types"
    (it "contains Human vs. Human"
      (let [player-type (:1 (available-player-types))]
        (should= "Human vs. Human"
                 (:description player-type))
        (should (:a (:players player-type)))
        (should (:b (:players player-type)))
        ))

    (it "contains Human vs. Computer"
      (let [player-type (:2 (available-player-types))]
        (should= "Human vs. Computer"
                 (:description player-type))
        (should (:a (:players player-type)))
        (should (:b (:players player-type)))))

    (it "contains Computer vs. Human"
      (let [player-type (:3 (available-player-types))]
        (should= "Computer vs. Human"
                 (:description player-type))
        (should (:a (:players player-type)))
        (should (:b (:players player-type)))))

    (it "contains Computer vs. Computer"
      (let [player-type (:4 (available-player-types))]
        (should= "Computer vs. Computer"
                 (:description player-type))
        (should (:a (:players player-type)))
        (should (:b (:players player-type)))))))
