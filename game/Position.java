package game;

public interface Position {
    int getN();

    int getM();

    int getK();

    boolean isValid(Move move);

    Cell getCell(int r, int c);

    String toString();
}
