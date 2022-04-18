package minesweeper;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        MineField mineField = new MineField(Integer.parseInt(scanner.nextLine()));
        mineField.populateMinesCount();
//        mineField.hideBombs();
        mineField.printHiddemBombs();
        while(!mineField.allBombsAreMarked()) {
            System.out.println("Set/delete mine marks (x and y coordinates):");
            int[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            try {
                mineField.markBomb(input);
                mineField.printHiddemBombs();
            } catch (NumberIsNotBombException e){
                System.out.println(e.getMessage());
            }
        }
        mineField.printHiddemBombs();
        System.out.println("Congratulations! You found all the mines!");
    }
}
