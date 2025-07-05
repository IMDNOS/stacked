package algorithms;

import logic.Board;

import java.util.*;


public class UniformCost {


    Board root;
    Set<Integer> visited;

    PriorityQueue<Board> priorityQueue;


    public UniformCost(Board board) {
        this.root = board;

        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(value -> value.cost));
        priorityQueue.add(board);


        visited = new HashSet<>();

        root.hashCode();

        while (!priorityQueue.isEmpty()) {
            Board current = priorityQueue.poll();
            visited.add(current.hashCode);


            if (current.checkWining()) {
                Common.DisplaySolution(current, visited);
                break;
            }

            current.nextStates();

            pushToQueue(current, current.nextStates.up);
            pushToQueue(current, current.nextStates.left);
            pushToQueue(current, current.nextStates.down);
            pushToQueue(current, current.nextStates.right);

        }

    }



    private void pushToQueue(Board current, Board next) {
        if (!visited.contains(next.hashCode)) {
            next.father = current.clone();
            priorityQueue.add(next);
        }
    }


}





