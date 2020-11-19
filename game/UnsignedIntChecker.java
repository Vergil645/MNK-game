package game;

import java.io.EOFException;
import java.util.Scanner;

public class UnsignedIntChecker {
    public static int next(final Scanner in, final StringBuilder message) throws EOFException {
        if (checkInt(in)) {
            int var = in.nextInt();
            if (var < 0) {
                message.append("(!) ").append(var).append(" is not Unsigned Integer\n");
            }
            return var;
        } else {
            message.append("(!) ").append(in.next()).append(" is not Unsigned Integer\n");
            return -1;
        }
    }

    private static boolean checkInt(final Scanner in) throws EOFException {
        if (!in.hasNext()) {
            throw new EOFException("Error: unexpected end of input was reached");
        }
        return in.hasNextInt();
    }
}
