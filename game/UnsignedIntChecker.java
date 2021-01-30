package game;

import java.util.Scanner;

public class UnsignedIntChecker {
    public static int next(final Scanner in, final StringBuilder message) {
        if (checkInt(in)) {
            int var = in.nextInt();
            if (var < 0) {
                message.append(String.format("(!) %d is not Unsigned Integer%n", var));
            }
            return var;
        } else {
            message.append(String.format("(!) %s is not Unsigned Integer%n", in.next()));
            return -1;
        }
    }

    private static boolean checkInt(final Scanner in) {
        if (!in.hasNext()) {
            throw new RuntimeException("Unexpected end of input was reached");
        }
        return in.hasNextInt();
    }
}
