package tictactoe;

/**
 * Created by Usiel on 23.02.2016.
 */
public interface IStrategy {
    Move getBestMove(int[][] board, int playerId);
}
