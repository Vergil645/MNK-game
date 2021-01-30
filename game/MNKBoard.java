package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Position, Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final int m, n, k, cond;
    private int empty;
    protected final Cell[][] cells;
    private Cell turn;

    protected MNKBoard(int m, int n, int k, int cond, int empty, Cell turn) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cond = cond;
        this.empty = empty;
        this.cells = new Cell[n][m];
        this.turn = turn;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.UNUSED);
        }
    }

    public MNKBoard(int m, int n, int k, int cond) {
        this(m, n, k, cond, n * m, Cell.X);
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }

    public MNKBoard(int m, int n, int k) {
        this(m, n, k, 2 * k);
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        if (!isOnBoard(r, c)) {
            throw new IndexOutOfBoundsException(String.format("Cell %d %d is not on the board%n", r, c));
        }
        return cells[r][c];
    }

    @Override
    public boolean isValid(final Move move) {
        return isOnBoard(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    protected boolean isOnBoard(final int row, final int col) {
        return 0 <= row && row < n && 0 <= col && col < m && cells[row][col] != Cell.UNUSED;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");

        for (int c = 0; c < m; c++) {
            sb.append(String.format(" %2d", c + 1));
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(String.format("%2d", r + 1));
            for (int c = 0; c < m; c++) {
                sb.append(String.format("  %s", cells[r][c] != Cell.UNUSED ? SYMBOLS.get(cells[r][c]) : " "));
            }
        }
        return sb.toString();
    }

    @Override
    public Position getPosition() {
        return new ProxyPosition(this);
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (move == null) {
            throw new RuntimeException("Player's move is null - it is not possible");
        }

        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;

        int stonesInLine = Math.max(
            Math.max(
                check(move, -1, 0) + check(move, 1, 0) - 1,
                check(move, 0, -1) + check(move, 0, 1) - 1
            ),
            Math.max(
                check(move, -1, -1) + check(move, 1, 1) - 1,
                check(move, -1, 1) + check(move, 1, -1) - 1
            )
        );

        if (stonesInLine >= k) {
            return Result.WIN;
        }

        if (empty == 0) {
            return Result.DRAW;
        }

        if (stonesInLine >= cond) {
            return Result.ADDITIONAL_MOVE;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    protected int check(final Move move, final int drow, final int dcol) {
        int cnt = 0;
        int r = move.getRow();
        int c = move.getColumn();

        while (isOnBoard(r, c) && cells[r][c] == cells[move.getRow()][move.getColumn()]) {
            cnt++;
            r += drow;
            c += dcol;
        }
        return cnt;
    }
}
