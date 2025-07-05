package algorithms;

import console.MakeBoard;
import console.PrintBoard;
import logic.Board;
import logic.Place;

import java.util.LinkedList;
import java.util.Set;

public class Common {


    static void DisplaySolution(Board board, Set<Integer> visited) {


        LinkedList<Board> path = new LinkedList<>();
        LinkedList<Place> directions = new LinkedList<>();


        while (board != null) {
            path.add(board);
            board = board.father;
        }

        path = path.reversed();


        int i = 0;
        for (Board s : path) {
            if (i != 0) {
                System.out.println("\tᐯ\n\tᐯ\n\tᐯ\n\tᐯ  " + s.place + "\n\tᐯ\n\tᐯ\n\tᐯ \n");      //  https://symbl.cc/en/142F/
                directions.add(s.place);
            } else {
                System.out.println("The Zero State:");
            }

            PrintBoard.printGame(s.grid, false);
//            System.out.println(s.distance);
            System.out.println();
            i++;
        }

        System.out.println("Count of visited boards is : "+visited.size());


        System.out.println("Solution path: "+directions);
        System.out.println("Solution length : [ " +  directions.size() +" ]");


    }


}
