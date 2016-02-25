package TicTacToe;

import java.util.*;

/**
 * Created by Usiel on 23.02.2016.
 */
public class MoveNode {
    public final int[][] board;
    public final int winner;
    public final int playerId;
    public final boolean gameOver;
    public final Move move;
    public List<MoveNode> children;
    public MoveNode parent;
    private MoveNode bestMove;
    public int score;

    public MoveNode(int[][] board, int playerId, Move move) {
        this.board = board;
        this.playerId = playerId;
        this.winner = new BoardAnalyzer().getWinner(board);
        this.gameOver = new BoardAnalyzer().isGameOver(board);
        this.move = move;
        this.children = new LinkedList<>();
    }

    public void add(MoveNode child) {
        child.parent = this;
        this.children.add(child);
    }

    public void generateOutcomes() {
        if (!this.gameOver) {
            // game open
            for (Move m : getLegalMoves(this.board)) {
                int[][] hypoBoard = Player.copy(this.board);
                hypoBoard[m.getRow()][m.getCol()] = Player.alternatePlayerId(this.playerId);
                MoveNode child = new MoveNode(hypoBoard, Player.alternatePlayerId(this.playerId), m);
                child.generateOutcomes();
                this.add(child);
            }
        } else {
            this.score = new BoardAnalyzer().getWinner(this.board);
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

    public MoveNode getBestMove(int playerId) {
        if (playerId == 1) {
            //maximize
            this.bestMove = getMaxMoveNode();
        } else {
            //minimize
            this.bestMove =  getMinMoveNode();
        }

        return bestMove;
    }

    public MoveNode getMinMoveNode() {
        if (gameOver) {
            this.score = this.winner;
            return this;
        }
        MoveNode current;
        MoveNode min = null;
        for (MoveNode n : children) {
            current = n.getMaxMoveNode();
            if (min == null || current.winner < min.winner) {
                min = n;
            }
        }
        this.score = min.score;
        this.bestMove = min;
        return min;
    }

    public MoveNode getMaxMoveNode() {
        if (gameOver) {
            this.score = this.winner;
            return this;
        }
        MoveNode current;
        MoveNode max = null;
        for (MoveNode n : children) {
            current = n.getMinMoveNode();
            if (max == null || current.winner > max.winner) {
                max = n;
            }
        }
        this.score = max.score;
        this.bestMove = max;
        return max;
    }
}

