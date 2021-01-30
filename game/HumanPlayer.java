package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.format("Position%n");
            out.format("%s%n", position);
            out.format("Play up to %d stones in row, column or diagonal%n", position.getK());
            out.format("%s's move%n", cell);

            StringBuilder message = new StringBuilder();

            out.format("Enter row and column: ");
            int row = UnsignedIntChecker.next(in, message);
            int col = UnsignedIntChecker.next(in, message);

            if (message.length() > 0) {
                out.format("Input is incorrect:%n%s", message.toString());
                continue;
            }

            final Move move = new Move(row - 1, col - 1, cell);
            if (position.isValid(move)) {
                return move;
            }

            out.format("Move %s is incorrect%n", move);
        }
    }
}
