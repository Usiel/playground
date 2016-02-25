package tictactoe;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Usiel on 23.02.2016.
 */
public class RandomStrategy implements IStrategy {
    @Override
    public Move getBestMove(int[][] board, int playerId) {
        // build tree of possible moves, each leaf is a game where getWinner > -1
        //root = new MoveNode(board);

        int i = -1;
        int j = -1;
        do {
            i = ThreadLocalRandom.current().nextInt(0, 3);
            j = ThreadLocalRandom.current().nextInt(0, 3);
        } while (board[i][j] > 0);

        return new Move(i, j);
    }
}
