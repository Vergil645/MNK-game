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

    /*
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (int r = 0; r < n / 2; r++) {
            if (r > 0) {
                sb.append("\n");
            }
            sb.append(" ".repeat(Math.max(0, n - 2 - 2 * r)));
            for (int c = n / 2 - r; c < n / 2 + r + 1; c++) {
                sb.append(" ").append(SYMBOLS.get(cells[r][c]));
            }
        }
        sb.append("\n");
        for (int c = 0; c < n; c++) {
            if (c > 0) {
                sb.append(" ");
            }
            sb.append(SYMBOLS.get(cells[n / 2][c]));
        }
        for (int r = n / 2 + 1; r < n; r++) {
            sb.append("\n").append(" ".repeat(2 * r - n));
            for (int c = r - n / 2; c < 2 * n - n / 2 - 1 - r; c++) {
                sb.append(" ").append(SYMBOLS.get(cells[r][c]));
            }
        }

        return sb.toString();
    }

     */
}
