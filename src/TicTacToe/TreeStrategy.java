package TicTacToe;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Usiel on 23.02.2016.
 */
public class TreeStrategy implements IStrategy {

    @Override
    public Move getBestMove(int[][] board, int playerId) {
        BoardAnalyzer analyzer = new BoardAnalyzer();
        // create tree of all move possibilities
        // choose node, with highest minimal gain (-1: lost, 0: draw, 1: won)
        MoveNode root = new MoveNode(Player.copy(board), Player.alternatePlayerId(playerId), null);
        root.generateOutcomes();

        Move m = root.getBestMove(playerId).move;
        return m;
    }
}
