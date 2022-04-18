package minesweeper;

public class NumberIsNotBombException extends Exception {
    public NumberIsNotBombException(String s) {
        super(s);
    }
}
