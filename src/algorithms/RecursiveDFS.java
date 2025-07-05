package algorithms;

import logic.Board;
import logic.NextStates;

import java.util.HashSet;
import java.util.Set;

public class RecursiveDFS {

    boolean found = false;

    Set<Integer> visited = new HashSet<>();


    public RecursiveDFS(Board board) {
        board.hashCode();
        tryIt(board);
    }

    void tryIt(Board current) {

        if (found || current==null)
            return;

        if(visited.contains(current.hashCode))
            return;

        visited.add(current.hashCode);

        if (current.checkWining()) {
                Common.DisplaySolution(current,visited);
            found=true;
        }


            NextStates nextStates = current.nextStates();

            nextStates.up.father = current.clone();
            tryIt(nextStates.up);

            nextStates.left.father = current.clone();
            tryIt(nextStates.left);

            nextStates.down.father = current.clone();
            tryIt(nextStates.down);

            nextStates.right.father = current.clone();
            tryIt(nextStates.right);

    }


}
