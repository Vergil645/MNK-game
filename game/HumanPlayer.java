package game;

import java.io.EOFException;
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
    public Move move(final Position position, final Cell cell) throws EOFException {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Play up to " + position.getK() + " stones in row, column or diagonal");
            out.println("Enter row and column");

            StringBuilder message = new StringBuilder();

            int row = UnsignedIntChecker.next(in, message);
            int col = UnsignedIntChecker.next(in, message);

            if (message.length() > 0) {
                out.print("Input is invalid:\n" + message.toString());
                continue;
            }

            final Move move = new Move(row - 1, col - 1, cell);
            if (position.isValid(move)) {
                return move;
            }

            out.println("Move " + move + " is invalid");
        }
    }
}
