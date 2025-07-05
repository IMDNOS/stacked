package logic;

import units.Color;
import units.Room;


public class Move {


    private static boolean changed = true;

    private static final int upCost = 5;
    private static final int leftCost = 10;
    private static final int downCost = 15;
    private static final int rightCost = 20;


    public static Board moveUp(Board board) {

        Board newBoard = board.clone();

        int x = newBoard.x;
        int y = newBoard.y;


        while (changed) {
            changed = false;

            for (int i = 1; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (newBoard.grid[i][j] instanceof Color) {
                        if (newBoard.grid[i - 1][j] instanceof Room) {
                            newBoard.grid[i - 1][j] = new Color(((Color) newBoard.grid[i][j]).symbol);
                            newBoard.grid[i][j] = new Room();
                            changed = true;
                        } else if (newBoard.grid[i - 1][j] instanceof Color && ((Color) newBoard.grid[i][j]).sameColor((Color) newBoard.grid[i - 1][j])) {
                            newBoard.grid[i][j] = new Room();
                            changed = true;
                        }
                    }
                }

            }
        }
        changed = true;


        newBoard.place = Place.up;
        newBoard.hashCode();
        newBoard.cost += upCost;

        return newBoard;
    }


    public static Board moveDown(Board board) {
        Board newBoard = board.clone();

        int x = newBoard.x;
        int y = newBoard.y;


        while (changed) {
            changed = false;

            for (int i = x - 2; i >= 0; i--) {
                for (int j = 0; j < y; j++) {
                    if (newBoard.grid[i][j] instanceof Color) {
                        if (newBoard.grid[i + 1][j] instanceof Room) {
                            newBoard.grid[i + 1][j] = new Color(((Color) newBoard.grid[i][j]).symbol);
                            newBoard.grid[i][j] = new Room();
                            changed = true;
                        } else if (newBoard.grid[i + 1][j] instanceof Color && ((Color) newBoard.grid[i][j]).sameColor((Color) newBoard.grid[i + 1][j])) {
                            newBoard.grid[i][j] = new Room();
                            changed = true;
                        }
                    }
                }
            }
        }
        changed = true;

        newBoard.place = Place.down;
        newBoard.hashCode();

        newBoard.cost += downCost;

        return newBoard;
    }

    public static Board moveLeft(Board board) {
        Board newBoard = board.clone();

        int x = newBoard.x;
        int y = newBoard.y;


        while (changed) {
            changed = false;

            for (int j = 1; j < y; j++) {
                for (int i = 0; i < x; i++) {
                    if (newBoard.grid[i][j] instanceof Color) {
                        if (newBoard.grid[i][j - 1] instanceof Room) {
                            newBoard.grid[i][j - 1] = new Color(((Color) newBoard.grid[i][j]).symbol);
                            newBoard.grid[i][j] = new Room();
                            changed = true;
                        } else if (newBoard.grid[i][j - 1] instanceof Color && ((Color) newBoard.grid[i][j]).sameColor((Color) newBoard.grid[i][j - 1])) {
                            newBoard.grid[i][j] = new Room();
                            changed = true;
                        }
                    }
                }
            }
        }
        changed = true;

        newBoard.place = Place.left;
        newBoard.hashCode();


        newBoard.cost += leftCost;

        return newBoard;

    }

    public static Board moveRight(Board board) {
        Board newBoard = board.clone();

        int x = newBoard.x;
        int y = newBoard.y;


        while (changed) {
            changed = false;

            for (int j = y - 2; j >= 0; j--) {
                for (int i = 0; i < x; i++) {
                    if (newBoard.grid[i][j] instanceof Color) {
                        if (newBoard.grid[i][j + 1] instanceof Room) {
                            newBoard.grid[i][j + 1] = new Color(((Color) newBoard.grid[i][j]).symbol);
                            newBoard.grid[i][j] = new Room();
                            changed = true;
                        } else if (newBoard.grid[i][j + 1] instanceof Color && ((Color) newBoard.grid[i][j]).sameColor((Color) newBoard.grid[i][j + 1])) {
                            newBoard.grid[i][j] = new Room();
                            changed = true;
                        }
                    }
                }
            }
        }
        changed = true;

        newBoard.place = Place.right;
        newBoard.hashCode();
        newBoard.cost += rightCost;

        return newBoard;
    }
}
