package console;

import algorithms.*;
import logic.Board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MakeBoard {


    Scanner in;

    Board board;

    public MakeBoard() {
        in = new Scanner(System.in);

        System.out.println("Load from file? [Y]");
        String s = in.next();

        if (s.charAt(0) == 'y' || s.charAt(0) == 'Y') {
            seed();
            PrintBoard.printGame(board.grid, false);
        } else {


            System.out.println("Enter number of rows");

            int x = in.nextInt();

            System.out.println("Enter number of columns");

            int y = in.nextInt();

            board = new Board(x, y);

            in.nextLine();


            addCell();

        }

        decide();

    }

    void decide() {

        System.out.println("Enter '1' to make the algorithm solve it\n" +
                "Enter 2 to play it yourself");

        int x = in.nextInt();


        board.calculateHeuristic();

        switch (x) {
            case 1:
                System.out.println("Choose your algorithm");
                System.out.println("1.BFS (Recommended)");
                System.out.println("2.loop DFS");
                System.out.println("3.Recursive DFS");
                System.out.println("4.Uniform-cost");
                System.out.println("5.Hill-Climbing");
                System.out.println("6.A*");

                int a = in.nextInt();

                switch (a) {
                    case 1:
                        new BFS(board);
                        break;

                    case 2:
                        new RepetitiveDFS(board);
                        break;

                    case 3:
                        new RecursiveDFS(board);
                        break;
                    case 4:
                        new UniformCost(board);
                        break;
                    case 5:
                        new HillClimbing(board);
                        break;
                    case 6:
                        new AStar(board);
                        break;
                }
                break;

            case 2:
                PrintBoard.printGame(board.grid, false);
                new Play(board);
                break;
        }

        System.out.println("\n\n");
        System.out.println("Wanna play again? [Y]");
        String s = in.next();

        if (s.charAt(0) == 'y' || s.charAt(0) == 'Y') {
            PrintBoard.printGame(board.grid, false);
            decide();
        }
    }


    void addCell() {

        PrintBoard.printGame(board.grid, true);

        System.out.println("Enter cell [ like :   2 1 #   ] , or type start to start playing");

        String input = in.nextLine();

        if (input.equals("start")) {
            decide();
            return;
        }


        String[] parts = input.split(" ");

        int num1, num2;
        char character;


        if (parts.length == 3) {
            try {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[1]);
                character = parts[2].charAt(0);


                if (num1 < 0 || num1 >= board.x) {
                    System.out.println("you are out of the boarder");
                    addCell();
                    return;
                }
                if (num2 < 0 || num2 >= board.y) {
                    System.out.println("you are out of the boarder");
                    addCell();
                    return;
                }
                if (!isAlpha(character) && character != '#' && character != '.') {
                    System.out.println("The color must be a letter!");
                    addCell();
                    return;
                }

                board.add(num1, num2, character);
                addCell();

            } catch (Exception e) {
                System.out.println("Please enter valid data.");
                addCell();
            }
        } else {
            System.out.println("Invalid input format. Please provide exactly three parts.");
            addCell();
        }
    }


    private boolean isAlpha(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }


    void seed() {
        String filePath = "src/console/seed.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            line=reader.readLine();
            String[] dimensions = line.split(" ");
            if (dimensions.length!=2)
                throw new IOException();
            int x=Integer.parseInt(dimensions[0]);
            int y=Integer.parseInt(dimensions[1]);
            board=new Board(x,y);


            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                if (parts.length == 3) {
                    int num1 = Integer.parseInt(parts[0]);
                    int num2 = Integer.parseInt(parts[1]);
                    char character = parts[2].charAt(0);

                    board.add(num1, num2, character);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


}
