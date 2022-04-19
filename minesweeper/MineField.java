package minesweeper;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class MineField {
    private int numMines;
    private String[][] field;
    private String[][] playerView;

    public MineField(int numMines) {
        this.numMines = numMines;
        field = populateField();
        playerView = copyField();
//        placeMines(numMines);
//        populateMinesCount();
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

    private void populateMinesCount() {
        for (int i = 2; i < this.field.length - 1; i++) {
            for (int j = 2; j < this.field[i].length - 1; j++) {
                if (!this.field[i][j].equals("X")) {
                    this.field[i][j] = calculateMinesCount(i, j);
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

    private void placeMines(int numMines, int guessRow, int guessCol) {
        Random random = new Random();
        for (int i = 0; i < numMines; i++) {
            int r = random.nextInt(field.length - 2) + 2;
            int c = random.nextInt(field[0].length - 2) + 2;
            if (!(r == guessRow && c == guessCol) && this.field[r][c].equals(".")) {
                this.field[r][c] = "X";
            } else {
                i--;
            }
        }
    }

    void printField() {
        printAnyField(this.field);
    }

    void printPlayerView() {
        printAnyField(this.playerView);
    }

    private void printAnyField(String[][] field) {
        for (String[] strings : field) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

     void markBomb(int[] input)  {
        //TODO indexes are replaced for testing
        int r = input[0];
        int c = input[1];
        //TODO implement some index check
        if (playerView[r][c].equals(".")) {
            playerView[r][c] = "*";
        } else if (playerView[r][c].equals("*")) {
            playerView[r][c] = ".";
        } else {
            //TODO refactor message to include "/"
//            throw new NumberIsNotBombException("There is a number here!");
            System.out.println("There is a number here!");
        }
    }

    void generateMines(int[] coordinates) {
        int r = coordinates[0];
        int c = coordinates[1];
        placeMines(this.numMines, r, c);
        populateMinesCount();

    }

    void guessFree(int[] coordinates) throws GameOverExeption {
        Deque<int[]> stackCoordinates = new ArrayDeque<>();
        stackCoordinates.push(coordinates);
        while (!stackCoordinates.isEmpty()) {
            processCoordinates(stackCoordinates.pop(), stackCoordinates);
        }

    }

    private void processCoordinates(int[] coordinates, Deque<int[]> stackCoordinates) throws GameOverExeption {
        int r = coordinates[0];
        int c = coordinates[1];
        if (field[r][c].matches("\\.")) {
            playerView[r][c] = "/";
            for (int i = r - 1; i <= r + 1; i++) {
                for (int j = c - 1; j <= c + 1; j++) {
                    //TODO think of something smarter
                    if (indexesAreValid(i, j) && (playerView[i][j].matches("\\.") || playerView[i][j].matches("\\*"))) {
                        stackCoordinates.push(new int[]{i, j});
                    }
                }
            }
        } else if (field[r][c].matches("X")) {
            copyMines();
            throw new GameOverExeption("You stepped on a mine and failed!");
        } else if (field[r][c].matches("\\d")) {
            playerView[r][c] = field[r][c];
        }

    }

    private void copyMines() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].equals("X")) {
                    playerView[i][j] = "X";
                }
            }
        }
    }

    boolean allBombsAreMarked() {
        int bombFlagCount = 0;
        for (int i = 2; i < playerView.length - 1; i++) {
            for (int j = 2; j < playerView[i].length - 1; j++) {
                if (playerView[i][j].equals("*") || playerView[i][j].equals(".")) {
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
