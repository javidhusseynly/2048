//Javid Husseynly, hussej3.

import java.util.Scanner;

public class Play {
    public static void main(String[] args) {
        Game board = new Game();
        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Play using 'wasd'.");


        boolean run = true;
        while (run) {
            String move = scanner.nextLine();
            System.out.println(move);
            if (move.equals("a")) {
                board.swipe_L();
                System.out.println("Swiped left");
                board.addRandom();
                board.printBoard();
                if (board.won()) {
                    System.out.println("You Won!");
                    run = false;
                }
                else if (board.lost()) {
                    System.out.println("You Lost");
                    run = false;
                }
            } else if (move.equals("d")) {
                board.swipe_R();
                System.out.println("Swiped right");
                board.addRandom();
                board.printBoard();
                if (board.won()) {
                    System.out.println("You Won!");
                    run = false;
                }
                else if (board.lost()) {
                    System.out.println("You Lost");
                    run = false;
                }
            } else if (move.equals("w")) {
                board.swipe_U();
                System.out.println("Swiped up");
                board.addRandom();
                board.printBoard();
                if (board.won()) {
                    System.out.println("You Won!");
                    run = false;
                }
                else if (board.lost()) {
                    System.out.println("You Lost");
                    run = false;
                }
            } else if (move.equals("s")) {
                board.swipe_D();
                System.out.println("Swiped down");
                board.addRandom();
                board.printBoard();
                if (board.won()) {
                    System.out.println("You Won!");
                    run = false;
                }
                else if (board.lost()) {
                    System.out.println("You Lost");
                    run = false;
                }
            } else {
                System.out.println("Invalid move, please use 'wasd'");
            }
        }

    }
}
