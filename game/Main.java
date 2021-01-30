package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            final Game game = new Game(true,
                    new HumanPlayer(),
                    new RandomPlayer()
            );

            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Enter n (edge), k and p");
                StringBuilder message = new StringBuilder();

                int n = UnsignedIntChecker.next(in, message);
                int k = UnsignedIntChecker.next(in, message);
                int p = UnsignedIntChecker.next(in, message);

                if (message.length() > 0) {
                    System.out.println("Input is invalid:");
                    System.out.println(message.toString());
                    continue;
                }

                if (n == 0) {
                    System.out.println("Input values are incorrect: n will be greater than 0");
                    continue;
                }

                if (k == 0) {
                    System.out.println("Input values are incorrect: k will be greater than 0");
                    continue;
                }

                if (k > 2 * n - 1) {
                    System.out.println("Input values are incorrect: k stones in line can not be reach - k is greater than 2n-1");
                    continue;
                }

                System.out.println("Game result: " + game.playWithAddMove(new RhombusBoard(n, k, p)));
                break;
            }
        } catch (RuntimeException e) {
            System.out.format("Game error: %s%n", e.getMessage());
        }
    }
}
