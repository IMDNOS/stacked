package logic;

import units.Cell;
import units.Color;
import units.Room;
import units.Wall;

import java.util.HashSet;
import java.util.Set;


public class Board {


    public Board father= null;
    public Place place;
    public int cost=0;
    public int heuristic =0;
    public int hashCode;


    public  int x;
    public  int y;
    public  Cell[][] grid;
    public NextStates nextStates;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;


        this.grid = new Cell[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.grid[i][j] = new Room();
            }
        }

        nextStates=new NextStates();
    }

    public void add(int i, int j, char c) {

        try {

            if (c == '.') {
                this.grid[i][j] = new Room();
            } else if (c == '#') {
                this.grid[i][j] = new Wall();
            } else {
                c = Character.toUpperCase(c);
                this.grid[i][j] = new Color(c);
            }

        }catch (Exception ignored){}
    }


    public Board clone(){

        Board newBoard=new Board(this.x,this.y);

        newBoard.place=this.place;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(this.grid[i][j] instanceof Wall)
                    newBoard.grid[i][j]=new Wall();
                else if (this.grid[i][j] instanceof Color) {
                    newBoard.grid[i][j] = new Color(((Color) this.grid[i][j]).getSymbol());
                }
            }
        }

        newBoard.heuristic =this.heuristic;

        if (this.father!=null)
            newBoard.father=this.father.clone();

        newBoard.hashCode=hashCode;
        newBoard.cost=cost;
        return newBoard;
    }


    public NextStates nextStates(){

        NextStates nextStates1=new NextStates();

        this.nextStates.up=Move.moveUp(this);
        nextStates1.up=Move.moveUp(this);



        this.nextStates.left=Move.moveLeft(this);
        nextStates1.left=Move.moveLeft(this);

        this.nextStates.down=Move.moveDown(this);
        nextStates1.down=Move.moveDown(this);

        this.nextStates.right=Move.moveRight(this);
        nextStates1.right=Move.moveRight(this);

        return nextStates1;
    }


/*
    public boolean equals(Board other){
        if(this.x!=other.x || this.y!=other.y)
            return false;

        int x = this.x;
        int y = this.y;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(this.grid[i][j] instanceof Room)
                    if(! (other.grid[i][j] instanceof Room))
                        return false;
                if(this.grid[i][j] instanceof Wall)
                    if(!(other.grid[i][j] instanceof Wall))
                        return false;
                if(this.grid[i][j] instanceof Color) {
                    if (!(other.grid[i][j] instanceof Color))
                        return false;
                    if (!(((Color) this.grid[i][j]).sameColor(((Color) other.grid[i][j]))))
                        return false;
                }
            }
        }

        return true;
    }
*/





    public boolean checkWining() {

        Set<Character> symbols = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] instanceof Color) {
                    if (symbols.contains(((Color) grid[i][j]).symbol))
                        return false;
                    symbols.add(((Color) grid[i][j]).symbol);
                }
            }
        }

        return true;
    }

    public void calculateHeuristic(){

        int cells=0;
        Set<Character> characterSet = new HashSet<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(grid[i][j] instanceof Color){
                    cells++;
                    characterSet.add(((Color) grid[i][j]).symbol);
                }
            }
        }

        heuristic =cells-characterSet.size();

    }

    public int hashCode(){

        String s="";

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(grid[i][j] instanceof Color)
                {
                    s+=String.valueOf(i);
                    s+="-";
                    s+=String.valueOf(j);
                    s+=((Color) grid[i][j]).symbol;
                }
            }
        }

        int h=s.hashCode();

        this.hashCode=h;

        return h;
    }




}
