package minesweeper;

import java.util.Random;

public class MineField {
    private int numMines;
    private String[][] field;

    public MineField(int numMines) {
        this.numMines = numMines;
        field = populateField();
        placeMines(numMines);
    }

    private String[][] populateField() {
        String[][]field = new String[9][9];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = ".";
            }
        }
        return field;
    }

    public void populateMinesCount(){
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[i].length; j++) {
                if (!this.field[i][j].equals("X")) {
                    this.field[i][j] = calculateMinesCount(i, j);
                }
            }
        }
    }

    private String calculateMinesCount(int r, int c) {
        int count = 0;
        for (int i = r -1; i <= r + 1 ; i++) {
            for (int j = c - 1; j <= c + 1 ; j++) {
                if (indexesAreValid(i, j) && !(i == r && j == c)) {
                    if (this.field[i][j].equals("X")) {
                        count++;
                    }
                }
            }
        }
        return count==0 ? "." : String.valueOf(count);
    }

    private boolean indexesAreValid(int i, int j) {
        return i >= 0 && i < this.field.length && j >= 0 && j < this.field[0].length;
    }

    private void placeMines(int numMines) {
        Random random = new Random();
        for (int i = 0; i < numMines; i++) {
            int r = random.nextInt(9);
            int c = random.nextInt(9);
            if (this.field[r][c].equals(".")) {
                this.field[r][c] = "X";
            } else {
                i--;
            }
        }
    }

    public void printFied() {
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[i].length; j++) {
                System.out.print(this.field[i][j]);
            }
            System.out.println();
        }
    }
}
