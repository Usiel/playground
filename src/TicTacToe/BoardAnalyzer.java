package TicTacToe;

import java.util.LinkedList;
import java.util.List;

public class BoardAnalyzer {

    public List<Line> getLines(int[][] board) {
        List<Line> lines = new LinkedList<>();
        for (int i=0; i<board.length; i++) {
            Line vertical = new Line();
            Line horizontal = new Line();
            for (int j=0; j<board.length; j++) {
                vertical.coordinates.add(new Pair(i,j));
                horizontal.coordinates.add(new Pair(i,j));
            }
            lines.add(vertical);
            lines.add(horizontal);
        }
        Line diagonal = new Line();
        Line diagonalBottomUp = new Line();
        for (int i=0; i<board.length; i++) {
            diagonal.coordinates.add(new Pair(i, i));
            diagonalBottomUp.coordinates.add(new Pair(board.length-1-i, i));
        }
        lines.add(diagonal);
        lines.add(diagonalBottomUp);
        return lines;
    }
    // -1: game open
    //  0: no winner
    //  x: Player x won
    public int getWinner(int[][] board) {
        int candidate;
        for (Line l : getLines(board)) {
            candidate = getWinner(l, board);
            if (candidate != 0) {
                return candidate;
            }
        }

        return 0;
    }

    private int getWinner(Line l, int[][] board) {
        if (l.coordinates.stream().allMatch(p -> board[p.row][p.col] == 1)) {
            return Game.PLAYER_1_WINNING_SCORE;
        }
        if (l.coordinates.stream().allMatch(p -> board[p.row][p.col] == 2)) {
            return Game.PLAYER_2_WINNER_SCORE;
        }

        return 0;
    }

    public Move getWinningMove(int[][] board, int playerId) {
        return getMoveToWinOrBlock(board, playerId, 1);
    }

    private Move getMoveToWinOrBlock(int[][] board, int playerId, int maxEmptyFields) {
        for (Line l : getLines(board)) {
            // check if line has all but one filled with playerId
            int allowed = maxEmptyFields;
            Move m = null;
            for (Pair p : l.coordinates) {
                int current = board[p.row][p.col];
                if (current == Player.alternatePlayerId(playerId) || allowed < 0) {
                    return null;
                }
                if (current != playerId) {
                    allowed--;
                    m = new Move(p.row, p.col);
                }
            }
            if (m != null && allowed == 0) {
                return m;
            }
        }
        return null;
    }

    public Move getWinBlockingMove(int[][] board, int playerId) {
        return getMoveToWinOrBlock(board, Player.alternatePlayerId(playerId), 1);
    }

    public Move getTwoWinMovesMove(int[][] board, int playerId) {
        return null;
    }

    public Move getMiddleMove(int[][] board, int playerId) {
        if (board[board.length/2][board.length/2] == 0) {
            return new Move(board.length/2, board.length/2);
        }
        return null;
    }

    public Move getWinningMoveForNextRoundMove(int[][] board, int playerId) {
        return getMoveToWinOrBlock(board, playerId, 2);
    }

    public Move getTwoWinMovesBlockingMove(int[][] board, int playerId) {
        return null;
    }

    public boolean isGameOver(int[][] board) {
        if (getWinner(board) != 0) {
            return true;
        }

        // no winner, but is it full?
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (board[i][j] == 0) {
                    //not full!
                    return false;
                }
            }
        }
        // must be full
        return true;
    }

}
