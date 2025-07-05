package algorithms;

import logic.Board;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class RepetitiveDFS {

    Board root;

    Stack<Board> stack;
    Set<Integer> visited;

    public RepetitiveDFS(Board board) {
        this.root = board;

        stack = new Stack<>();
        stack.add(board);

        visited = new HashSet<>();


        root.hashCode();

        while (!stack.isEmpty()) {
            Board current = stack.pop();
            visited.add(current.hashCode);

            if (current.checkWining()) {
                Common.DisplaySolution(current, visited);
                break;
            }

            current.nextStates();

            pushToStack(current, current.nextStates.right);
            pushToStack(current, current.nextStates.down);
            pushToStack(current, current.nextStates.left);
            pushToStack(current, current.nextStates.up);
        }
    }


    private void pushToStack(Board current, Board next) {
        if (!visited.contains(next.hashCode)) {
            next.father = current.clone();
            stack.push(next);
        }
    }
}
