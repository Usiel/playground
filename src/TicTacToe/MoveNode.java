package TicTacToe;

import java.util.*;

/**
 * Created by Usiel on 23.02.2016.
 */
public class MoveNode {
    public final int[][] board;
    public final int winner;
    public final int playerId;
    public final Move move;
    public List<MoveNode> children;
    public MoveNode parent;

    public MoveNode(int[][] board, int playerId, Move move) {
        this.board = board;
        this.playerId = playerId;
        this.winner = new BoardAnalyzer().getWinner(board);
        this.move = move;
        this.children = new LinkedList<>();
    }

    public void add(MoveNode child) {
        child.parent = this;
        child.generateOutcomes();
        this.children.add(child);
    }

    public void pruneBadSubtrees() {
        int playerToWin = Player.alternatePlayerId(this.playerId);
        List<MoveNode> lost = search(Player.alternatePlayerId(playerToWin));

        for (MoveNode m : lost) {
            // remove this node's parent from this tree
            m.detachParent();
        }
    }

    public void pruneNeutralSubtrees() {
        List<MoveNode> remis = searchRemis();

        for (MoveNode m : remis) {
            m.detachParent();
        }
    }

    private List<MoveNode> searchRemis() {
        return search(0);
    }

    private void detachParent() {
        if (this.parent != null && this.parent.parent != null) {
            this.parent.parent.children.remove(this.parent);
        }
    }

    private List<MoveNode> search(int outcome) {
        List<MoveNode> matchingOutcomes = new LinkedList<>();
        if (winner == outcome) {
            //opponent wins!
            matchingOutcomes.add(this);
            // no need to return subtrees
            return matchingOutcomes;
        }

        for (MoveNode m : children) {
            matchingOutcomes.addAll(m.search(outcome));
        }

        return matchingOutcomes;
    }

    public void generateOutcomes() {
        if (this.winner == -1) {
            // game open
            for (Move m : getLegalMoves(this.board)) {
                int[][] hypoBoard = Player.copy(this.board);
                hypoBoard[m.getRow()][m.getCol()] = Player.alternatePlayerId(this.playerId);
                this.add(new MoveNode(hypoBoard, Player.alternatePlayerId(this.playerId), m));
            }
        }
    }

    private List<Move> getLegalMoves(int[][] board) {
        List<Move> legalMoves = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    legalMoves.add(new Move(i, j));
                }
            }
        }
        return legalMoves;
    }

    public Move findQuickestPathTo(int winner) {
        if (this.winner == winner) {
            return this.move;
        }

        Queue<MoveNode> toVisit = new ArrayDeque<>();
        toVisit.addAll(children);
        //which move to winner outcome
        while (toVisit.peek() != null) {
            MoveNode current = toVisit.poll();

            if (current.winner == winner) {
                while (current.parent != this) {
                    current = current.parent;
                }

                return current.move;
            }

            toVisit.addAll(current.children);
        }

        return null;
    }
}

