package game;

public interface Position {
    int getN();

    int getM();

    int getK();

    Cell getCell(int r, int c);

    boolean isValid(Move move);

    String toString();
}
