package console;

import logic.Board;
import logic.Move;

import java.util.Scanner;

public class Play {


    Board board;

    Board backupBoard;

    public Play(Board board) {
        this.board=board;
        backupBoard=board.clone();
        play();
    }


    public void play() {
        Scanner in = new Scanner(System.in);
        System.out.println("Press W, A, S, or D to move, or Q to quit.");

        while (true) {
            String input = in.nextLine().trim().toLowerCase();

            switch (input) {
                case "w":
                    board=Move.moveUp(board);
                    PrintBoard.printGame(board.grid, false);
                    break;
                case "a":
                    board=Move.moveLeft(board);
                    PrintBoard.printGame(board.grid, false);
                    break;
                case "s":
                    board=Move.moveDown(board);
                    PrintBoard.printGame(board.grid, false);
                    break;
                case "d":
                    board=Move.moveRight(board);
                    PrintBoard.printGame(board.grid, false);
                    break;
                case "r":
                    board=backupBoard.clone();
                    PrintBoard.printGame(board.grid, false);
                    break;

                case "q":
                    System.out.println("Exiting game.");
                    return;
                default:
                    System.out.println("Press W, A, S, or D to move,R to Restart, or Q to quit.");
            }

            if (board.checkWining()) {

                System.out.println("___!!!_YOU WON_!!!___");

                break;
            }
        }


    }




}
