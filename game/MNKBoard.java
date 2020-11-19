package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Position, Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final int m, n, k;
    private int empty;
    private final Cell[][] cells;
    private Cell turn;

    public MNKBoard(final int m, final int n, final int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.empty = n * m;
        this.cells = new Cell[n][m];

        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return new ProxyPosition(this);
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

    public boolean isValid(final Move move) {
        return move != null
                && 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (int r = 0; r < n; r++) {
            if (r > 0) {
                sb.append("\n");
            }
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;

        if (check(move, -1, 0) + check(move, 1, 0) - 1 >= k // vertical line
            || check(move, 0, -1) + check(move, 0, 1) - 1 >= k // horizontal line
            || check(move, -1, -1) + check(move, 1, 1) - 1 >= k // main diagonal
            || check(move, -1, 1) + check(move, 1, -1) - 1 >= k) { // secondary diagonal
            return Result.WIN;
        }

        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private int check(final Move move, final int drow, final int dcol) {
        int cnt = 0;
        int r = move.getRow();
        int c = move.getColumn();

        while (0 <= r && r < n && 0 <= c && c < m && cells[r][c] == cells[move.getRow()][move.getColumn()]) {
            cnt++;
            r += drow;
            c += dcol;
        }
        return cnt;
    }
}
