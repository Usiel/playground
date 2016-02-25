package tictactoe;

/**
 * Created by Usiel on 23.02.2016.
 */
public class Move {
    private final int col;
    private final int row;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
