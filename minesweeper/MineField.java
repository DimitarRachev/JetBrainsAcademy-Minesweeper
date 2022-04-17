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
