package game;

import java.io.EOFException;

public interface Player {
    Move move(Position position, Cell cell) throws EOFException;
}
