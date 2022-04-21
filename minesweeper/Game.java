package minesweeper;

import java.util.Scanner;

public class Game {
    MineField mineField;
    Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);

    }

    void start() throws GameOverExeption {
        System.out.print("How many mines do you want on the field? ");
      this.mineField = new MineField(Integer.parseInt(scanner.nextLine().trim()));
        roll();
    }

    private void roll() throws GameOverExeption {
        mineField.printField();
        boolean firstMineGuest = false;
        while(!gameIsOver()) {
            System.out.println("Set/unset mines marks or claim a cell as free: ");
            System.out.println("Use format \"row column free/mine\"");
            String[] tokens = scanner.nextLine().split("\\s+");
            int[] coordinates = new int[]{Integer.parseInt(tokens[0]) + 1, Integer.parseInt(tokens[1]) + 1};
            if (tokens[2].equals("free")) {
                if (!firstMineGuest) {
                    mineField.generateMines(coordinates);
                    firstMineGuest = true;
                }
                mineField.guessFree(coordinates);
            } else if (tokens[2].equals("mine")) {
                mineField.markBomb(coordinates);
            }
            mineField.printPlayerView();
        }
        throw new GameOverExeption("Congratulations! You found all the mines!");
    }

    private boolean gameIsOver() {

        return mineField.allBombsAreMarked();
    }

    public void printFinalField() {
            mineField.printPlayerView();
    }
}
