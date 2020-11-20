package game;

import java.io.EOFException;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(final Board board) throws EOFException {
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 >= 0) {
                return result1;
            }

            final int result2 = move(board, player2, 2);
            if (result2 >= 0) {
                return result2;
            }
        }
    }

    public int playWithAddTurn(final Board board) throws EOFException {
        while (true) {
            int result;

            do {
                result = move(board, player1, 1);
            } while (result == -2);
            if (result >= 0) {
                return result;
            }

            do {
                result = move(board, player2, 2);
            } while (result == -2);
            if (result >= 0) {
                return result;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) throws EOFException {
        final Move move = player.move(board.getPosition(), board.getTurn());
        final Result result = board.makeMove(move);

        log(String.format("Player %d move: %s", no, move));
        log(String.format("Position:%n%s", board));

        if (result == Result.WIN) {
            log(String.format("Player %d won", no));
            return no;
        } else if (result == Result.LOSE) {
            log(String.format("Player %d lose", no));
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else if (result == Result.UNKNOWN) {
            log("Unknown");
            return -1;
        } else if (result == Result.ADDITIONAL_TURN) {
            log(String.format("Additional move of player %d", no));
            return -2;
        } else {
            throw new AssertionError("Impossible");
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
