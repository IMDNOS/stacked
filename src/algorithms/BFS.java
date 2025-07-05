package algorithms;

import logic.Board;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    Board root;

    Queue<Board> queue;
    Set<Integer> visited;

    public BFS(Board board) {
        this.root = board;

        queue = new LinkedList<>();
        queue.add(board);

        visited = new HashSet<>();

        root.hashCode();

        while (!queue.isEmpty()) {
            Board current = queue.poll();
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
            queue.add(next);
        }
    }
}