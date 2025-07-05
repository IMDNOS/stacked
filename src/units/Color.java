package units;

public class Color extends Cell {

    public final char symbol;

    public Color(char symbol) {
        this.symbol = symbol;
    }

    public boolean sameColor(Color color){
        return this.symbol == color.symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
