package game;

public interface Board {
    Position getPosition();

    Cell getTurn();

    Result makeMove(Move move);

    String toString();
}
