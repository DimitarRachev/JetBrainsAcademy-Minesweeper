package minesweeper;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("How many mines do you want on the field? ");
//        MineField mineField = new MineField(Integer.parseInt(scanner.nextLine()));
//        mineField.populateMinesCount();

//        mineField.printHiddenBombs();
        Game game = new Game();
        try {
            game.start();
        } catch (GameOverExeption e) {
            game.printFinalField();
            System.out.println(e.getMessage());
        }
//
//        while(!mineField.allBombsAreMarked()) {
//            System.out.println("Set/unset mines marks or claim a cell as free: ");
//            int[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
//                    .mapToInt(Integer::parseInt)
//                    .toArray();
//            try {
//                mineField.markBomb(input);
//                mineField.printHiddenBombs();
//            } catch (NumberIsNotBombException e){
//                System.out.println(e.getMessage());
//            }
//        }
//        mineField.printHiddenBombs();
//        System.out.println("Congratulations! You found all the mines!");

    }
}
