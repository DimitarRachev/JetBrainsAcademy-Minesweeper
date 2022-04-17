package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        MineField mineField = new MineField(scanner.nextInt());
        mineField.populateMinesCount();
        mineField.printFied();

    }
}
