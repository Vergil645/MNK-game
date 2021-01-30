package game;

import java.util.Arrays;

public class RhombusBoard extends MNKBoard {
    public RhombusBoard(int n, int k, int cond) {
        super(2 * n - 1, 2 * n - 1, k, cond, 2 * n * (n - 1) + 1, Cell.X);

        for (int r = 0; r < 2 * n - 1; r++) {
            int delta = Math.min(r, 2 * (n - 1) - r);
            Arrays.fill(cells[r], n - 1 - delta, n + delta, Cell.E);
        }
    }
}
