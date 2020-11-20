package game;

import java.io.EOFException;
import java.util.Scanner;

public class UnsignedIntChecker {
    public static int next(final Scanner in, final StringBuilder message) throws EOFException {
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

    private static boolean checkInt(final Scanner in) throws EOFException {
        if (!in.hasNext()) {
            throw new EOFException("Unexpected end of input was reached");
        }
        return in.hasNextInt();
    }
}
