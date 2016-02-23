package TicTacToe;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Usiel on 21.02.2016.
 */
public class Game {
    private Player p1;
    private Player p2;
    private BoardAnalyzer analyzer;
    public int[][] board = new int[3][3];

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }

    public Game() {
        p1 = new Player(1, new TreeStrategy());
        p2 = new Player(2, new TreeStrategy());
        analyzer = new BoardAnalyzer();
    }

    public void play() {
        Player p = p1;
        while (analyzer.getWinner(board) == -1) {
            Move m = p.move(board.clone());
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

class BoardAnalyzer {
    // -1: game open
    //  0: no winner
    //  x: Player x won
    public int getWinner(int[][] board) {
        int candidate;
        for (int i=0 ; i<board.length; i++) {
            candidate = getWinner(board[0][i], board[1][i], board[2][i]);
            if (candidate > -1) {
                return candidate;
            }
            candidate = getWinner(board[i][0], board[i][1], board[i][2]);
            if (candidate > -1) {
                return candidate;
            }
        }

        candidate = getWinner(board[0][0], board[1][1], board[2][2]);
        if (candidate > -1) {
            return candidate;
        }

        candidate = getWinner(board[0][2], board[1][1], board[2][0]);
        if (candidate > -1) {
            return candidate;
        }

        // no winner, but is it full?
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (board[i][j] == 0) {
                    return -1;
                }
            }
        }
        // must be full
        return 0;
    }

    private int getWinner(int a, int b, int c) {
        if (a == b && b == c && a != 0) {
            return a;
        } else {
            return -1;
        }
    }
}