package algorithms;

import logic.Board;

import java.util.*;

public class HillClimbing {


    Board root;
    PriorityQueue<Board> priorityQueue;
    Set<Integer> visited;


    public HillClimbing(Board board) {
        this.root = board;

        root.hashCode();


        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(value -> value.heuristic));
        priorityQueue.add(board);

        visited = new HashSet<>();

        while (!priorityQueue.isEmpty()) {

            Board current = priorityQueue.poll();
            visited.add(current.hashCode);

            if (current.checkWining()) {
                Common.DisplaySolution(current,visited);
                break;
            }
            current.nextStates();


            pushToQueue(current,current.nextStates.up);
            pushToQueue(current,current.nextStates.left);
            pushToQueue(current,current.nextStates.down);
            pushToQueue(current,current.nextStates.right);

        }

    }





    private void pushToQueue (Board current, Board next){

        if (!visited.contains(next.hashCode)) {
            next.father = current.clone();
            priorityQueue.add(next);
        }
    }


}
