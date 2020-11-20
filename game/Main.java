package game;

import java.io.EOFException;

public class Main {
    public static void main(String[] args) {
        try {
            final Game game = new Game(true,
                    new RandomPlayer(),
                    new HumanPlayer()
            );

            System.out.format("Game result: %s%n", game.playWithAddTurn(new RhombusBoard(7, 6, 4)));

            /*
            try (Scanner in = new Scanner(System.in)) {
                while (true) {
                    System.out.println("Enter m (column count), n (row count), k");
                    StringBuilder message = new StringBuilder();

                    int m = UnsignedIntChecker.next(in, message);
                    int n = UnsignedIntChecker.next(in, message);
                    int k = UnsignedIntChecker.next(in, message);

                    if (message.length() > 0) {
                        System.out.println("Input is invalid:");
                        System.out.println(message.toString());
                        continue;
                    }

                    if (m == 0 || n == 0 || k == 0 || k > Math.max(m, n)) {
                        System.out.println("Input values are incorrect");
                        continue;
                    }

                    System.out.println("Game result: " + game.play(new MNKBoard(m, n, k)));
                    break;
                }
            } catch (EOFException e) {
                System.out.println(e.getMessage());
            }
             */
        } catch (EOFException e) {
            System.out.format("EOF error: %s%n", e.getMessage());
        }
    }
}
