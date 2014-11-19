(defproject tictactoe "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe"
  :url "https://github.com/christophgockel/tictactoe-clojure"
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :plugins [[speclj "3.1.0"]]
  :profiles {:dev {:dependencies [[speclj "3.1.0"]]}}
  :test-paths ["spec"])
