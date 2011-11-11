package dish;

/**
 * A naive implementation of the game of life in Java using the following rules:
 *
 * <ul>
 *  <li>Any live cell with fewer than two live neighbours dies, as if caused by under-population.</li>
 *  <li>Any live cell with two or three live neighbours lives on to the next generation.</li>
 *  <li>Any live cell with more than three live neighbours dies, as if by overcrowding.</li>
 *  <li>Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.</li>
 * </ul>
 *
 * @link http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 */
public class Dish {
	private boolean[][] life;

    /**
     * Create a rectangular Petri dish of binary life.
     *
     * @param life two dimensional binary array, true for living cell.
     * @throws IllegalArgumentException if all the rows of life are not the same length, or there is no life in the array
     */
    public Dish(boolean[][] life) {
        if (life.length == 0) {
            throw new IllegalArgumentException("No Life");
        }
        for (boolean[] aRowOfLife: life) {
            if (aRowOfLife.length != life[0].length) {
                throw new IllegalArgumentException("All rows in life need to be the same length.");
            }
        }
        this.life = life;
    }

    /**
     * Print the dish out as a String, a line for each row.
     * Display 1 for living cells otherwise 0 for dead cell.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (boolean[] aRowOfLife : life) {
            for (boolean aCellOfLife: aRowOfLife) {
                sb.append(aCellOfLife ? "1" : "0");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Dish && toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public Dish evolve() {
        boolean[][] newLife = new boolean[life.length][life[0].length];

        for (int row = 0; row < life.length; row++) {
            for (int col = 0; col < life[row].length; col++) {
                int count = livingNeighbours(row, col);

                // rules of life
                if (life[row][col]) { // for the living
                    newLife[row][col] = !(count < 2 || count > 3);
                } else { // for the dead
                    newLife[row][col] = count == 3;
                }
            }
        }

        return new Dish(newLife);
    }

    private int livingNeighbours(int row, int col) {
        int count = 0;

        int[] search = { -1, 0, 1 };
        for (int i : search) {
            for (int j : search) {
                if ((i  == 0 && j == 0)
                        || ((i + row < 0) || (j + col < 0))
                        || ((i + row > life.length - 1) || (j + col > life[i + row].length - 1))
                        ) {
                    // at the centre (current cell)
                    // or off the dish (top or left)
                    // or off the dish (bottom or right) do nothing
                } else {
                    // dead+0 or alive+1?
                    count = count + (life[i+row][j+col]?1:0);
                }
            }
        }
        return count;
    }
}