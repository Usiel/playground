package TicTacToe;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Usiel on 23.02.2016.
 */
public class TreeStrategy implements IStrategy {
    private MoveNode root;
    private int playerId;

    @Override
    public Move getBestMove(int[][] board, int playerId) {
        BoardAnalyzer analyzer = new BoardAnalyzer();
        this.playerId = playerId;
        // create tree of all move possibilities
        // choose node, with highest minimal gain (-1: lost, 0: draw, 1: won)
        root = new MoveNode(board, Player.alternatePlayerId(playerId), null);
        root.generateOutcomes();

        // at root opponent is playing
        Move firstBestMove = root.children.get(0).move;
        root.pruneBadSubtrees();
        if (root.children.size() == 0) {
            // we lost, no move does not end in a loss
            return firstBestMove;
        }
        firstBestMove = root.findQuickestPathTo(playerId);
        if (firstBestMove == null) {
            firstBestMove = root.children.get(0).move;
        }
        root.pruneNeutralSubtrees();
        if (root.children.size() == 0) {
            // ok draw
            return firstBestMove;
        }

        //there are still nodes left, of which all lead to a win (we choose the closest!)
        return root.findQuickestPathTo(playerId);
        // we prune the tree: every time a node is found where opponent can play and win we remove the direct subtree that this node is part of
    }
}
