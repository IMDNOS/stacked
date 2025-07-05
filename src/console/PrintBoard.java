package console;

import units.Cell;
import units.Color;
import units.Room;
import units.Wall;

public class PrintBoard {




    public static void printGame(Cell[][] grid , boolean make) {

        int x=grid.length;
        int y=grid[0].length;


        if (make) {
            System.out.print("   ");
            for (int j = 0; j < y; j++)
                System.out.print(j + " ");
            System.out.println();
            System.out.print(" ");
        }


        System.out.print(" =");
        for (int j = 0; j < y; j++)
            System.out.print("==");
        System.out.println();


        for (int i = 0; i < x; i++) {
            if (make)
                System.out.print(i);
            System.out.print("║ ");
            for (int j = 0; j < y; j++) {
                Cell cell = grid[i][j];


                if (cell instanceof Room) {
                    System.out.print(". ");
                } else if (cell instanceof Wall) {
                    System.out.print("# ");
                } else if (cell instanceof Color) {
                    System.out.print(((Color) cell).symbol + " ");
                }
            }
            System.out.println("║");
        }


        if (make)
            System.out.print(" ");

        System.out.print(" =");
        for (int j = 0; j < y; j++)
            System.out.print("==");
        System.out.println();


    }


}
