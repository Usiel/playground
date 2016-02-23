package TicTacToe;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Usiel on 21.02.2016.
 */
public class Game {
    private Player p1;
    private Player p2;
    private BoardAnalyzer analyzer;
    public int[][] board;

    public static void main(String[] args) {
        Game g = new Game(3);
        g.play();
    }

    public Game(int size) {
        p1 = new Player(1, new TreeStrategy());
        p2 = new Player(2, new ExplicitStrategy());
        analyzer = new BoardAnalyzer();
        board = new int[size][size];
    }

    public void play() {
        Player p = p1;
        while (analyzer.getWinner(board) == -1) {
            Move m = p.move(board.clone());
            if (m == null) {
                m = new RandomStrategy().getBestMove(board, p.id);
            }
            board[m.getRow()][m.getCol()] = p.id;
            System.out.println("round over");
            p = p == p1 ? p2 : p1;
            print();
        }
        System.out.println("game over");
        System.out.println(analyzer.getWinner(board));
    }

    public void print() {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }
}

