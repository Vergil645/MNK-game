package game;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;

        if (player1 == null) {
            throw new RuntimeException("There isn't player 1, game can not be started");
        }

        if (player2 == null) {
            throw new RuntimeException("There isn't player 2, game can not be started");
        }
    }

    public int play(final Board board) {
        checkBoard(board);

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

    public int playWithAddMove(final Board board) {
        checkBoard(board);

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

    private void checkBoard(final Board board) {
        if (board == null) {
            throw new RuntimeException("There is no board");
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getTurn());
        final Result result;

        try {
            result = board.makeMove(move);
        } catch (RuntimeException e) {
            throw new RuntimeException(String.format("Player %d move is null - it is not possible", no));
        }

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
        } else if (result == Result.ADDITIONAL_MOVE) {
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
