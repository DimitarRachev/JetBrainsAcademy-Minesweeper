package minesweeper;

import java.util.Random;

public class MineField {
    private int numMines;
    private String[][] field;
    private String[][] noBombs;

    public MineField(int numMines) {
        this.numMines = numMines;
        field = populateField();
        noBombs = copyField();
        placeMines(numMines);
        populateMinesCount();
    }

    private String[][] copyField() {
        String[][] field = new String[12][12];
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field.length; j++) {
                field[i][j] = this.field[i][j];
            }
        }
        return field;
    }

    private String[][] populateField() {
        String[][] field = new String[12][12];
        field[0] = new String[]{" ", "|", "1", "2", "3", "4", "5", "6", "7", "8", "9", "|"};
        field[1] = new String[]{"-", "|", "-", "-", "-", "-", "-", "-", "-", "-", "-", "|"};
        field[field.length - 1] = new String[]{"-", "|", "-", "-", "-", "-", "-", "-", "-", "-", "-", "|"};
        for (int i = 2; i < field.length - 1; i++) {
            field[i] = new String[]{String.valueOf(i - 1), "|", ".", ".", ".", ".", ".", ".", ".", ".", ".", "|"};
        }
        return field;
    }

    public void populateMinesCount() {
        for (int i = 2; i < this.field.length - 1; i++) {
            for (int j = 2; j < this.field[i].length - 1; j++) {
                if (!this.field[i][j].equals("X")) {
                    this.field[i][j] = calculateMinesCount(i, j);
                    this.noBombs[i][j] = this.field[i][j];
                }
            }
        }
    }

    private String calculateMinesCount(int r, int c) {
        int count = 0;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (indexesAreValid(i, j) && !(i == r && j == c)) {
                    if (this.field[i][j].equals("X")) {
                        count++;
                    }
                }
            }
        }
        return count == 0 ? "." : String.valueOf(count);
    }

    private boolean indexesAreValid(int i, int j) {
        return i >= 2 && i < this.field.length - 1 && j >= 2 && j < this.field[0].length - 1;
    }

    private void placeMines(int numMines) {
        Random random = new Random();
        for (int i = 0; i < numMines; i++) {
            int r = random.nextInt(field.length - 2) + 2;
            int c = random.nextInt(field[0].length - 2) + 2;
            if (this.field[r][c].equals(".")) {
                this.field[r][c] = "X";
            } else {
                i--;
            }
        }
    }

    public void printField() {
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[i].length; j++) {
                System.out.print(this.field[i][j]);
            }
            System.out.println();
        }
    }

    public void printHiddenBombs() {
        for (int i = 0; i < this.noBombs.length; i++) {
            for (int j = 0; j < this.noBombs[i].length; j++) {
                System.out.print(this.noBombs[i][j]);
            }
            System.out.println();
        }
    }

    public void hideBombs() {
        for (int i = 0; i < noBombs.length; i++) {
            for (int j = 0; j < noBombs.length; j++) {
                if (noBombs[i][j].equals("X")) {
                    noBombs[i][j] = ".";
                }
            }
        }
    }

    public void markBomb(int[] input) throws NumberIsNotBombException {
        //TODO indexes are replaced for testing
        int r = input[1] + 1;
        int c = input[0] + 1;
        //TODO implement some index check
        if (noBombs[r][c].equals(".")) {
            noBombs[r][c] = "*";
        } else if (noBombs[r][c].equals("*")) {
            noBombs[r][c] = ".";
        } else {
            throw new NumberIsNotBombException("There is a number here!");
        }
    }

    public boolean allBombsAreMarked() {
        int bombFlagCount = 0;
        for (int i = 2; i < noBombs.length - 1; i++) {
            for (int j = 2; j < noBombs[i].length - 1; j++) {
                if (noBombs[i][j].equals("*")) {
                    if (field[i][j].equals("X")) {
                        bombFlagCount++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return bombFlagCount == numMines;
    }
}
