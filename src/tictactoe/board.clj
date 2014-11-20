(ns tictactoe.board)

(defn new-board [size]
  (into [] (for [x (range (* size size))] (+ x 1))))

(defn place-move [mark position board]
  (assoc board (- position 1) mark))

(defn- position-taken? [position board]
  (string? (nth board (- position 1))))

(defn is-valid-move? [position board]
  (not (position-taken? position board)))

(defn- width [board]
  (let [length (count board)]
    (int (Math/sqrt length))))

(defn- rows [board]
  (partition (width board) board))

(defn- is-unique? [line]
   (= 1 (count (distinct line))))

(defn- columns [board]
  (apply map list (rows board)))

(defn- diagonals [board]
  (partition
    (width board )
    (concat
      (map-indexed
        (fn [index item] (get (vec item) index))
        (rows board))
      (map-indexed
        (fn [index item] (get (vec item) index))
        (reverse (rows board))))))

(defn- line-combinations [board]
  (concat
    (rows board)
    (columns board)
    (diagonals board)))

(defn has-winner? [board]
  (> (count (filter is-unique? (line-combinations board))) 0))

(defn winner [board]
  (first
    (apply #(-> %)
           (filter is-unique? (line-combinations board)))))

