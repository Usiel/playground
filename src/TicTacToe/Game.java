package TicTacToe;

/**
 * Created by Usiel on 21.02.2016.
 */
public class Game {
    private Player p1;
    private Player p2;
    private BoardAnalyzer analyzer;
    private int[][] board = new int[3][3];

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
        g.print();
    }

    public Game() {
        p1 = new Player(1);
        p2 = new Player(2);
        analyzer = new BoardAnalyzer();
    }

    public void play() {
        Player p = p1;
        while (analyzer.getWinner(board) == -1) {
            p.move(board);
            System.out.println("round over");
            p = p == p1 ? p2 : p1;
        }
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

class Player {
    public int id;
    private BoardAnalyzer analyzer;

    public Player(int id) {
        this.id = id;
        analyzer = new BoardAnalyzer();
    }

    public int[][] copy(int[][] a) {
        int[][] c = new int[a.length][a.length];
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a[i].length; j++) {
                c[i][j] = a[i][j];
            }
        }
        return c;
    }

    public void move(int[][] board) {
        int best = -1;
        int bestI = 1;
        int bestJ = 1;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (board[i][j] == 0) {
                    int current = minMax(board, i, j, this.id);
                    if (current > best) {
                        best = current;
                        bestI = i;
                        bestJ = j;
                    }
                }
            }
        }
        System.out.println("Player " + this.id + " does [" + bestI + "," + bestJ + "]");
        board[bestI][bestJ] = this.id;
    }

    public int minMax(int[][] b, int r, int c, int playerId) {
        // -1 lost
        // 0 remis
        // 1 won

        int[][] board = copy(b);

        board[r][c] = playerId;

        int w = analyzer.getWinner(board);
        Integer max = 0;
        boolean maximize = playerId == this.id;

        if (w == playerId) {
            if (maximize) {
                return w;
            } else {
                max = -1;
            }
        } else if (w == playerId && playerId != this.id) {
            // enemy wins
            max = -1;
        } else if (w == 0) {
            if (0 < max) {
                max = 0;
            }
        } else { // game continues ...
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 0) {
                        int[][] copy = copy(board);
                        copy[i][j] = playerId;
                        int current = minMax(copy, i, j, playerId % 2 + 1);
                        if (current < max) {
                            max = current;
                        }
                    }
                }
            }
        }
        return max;
    }
}

class BoardAnalyzer {
    public int getWinner(int[][] board) {
        int winner = -1;
        int candidate;
        for (int i=0 ; i<board.length; i++) {
            candidate = getWinner(board[i]);
            if (candidate > winner) {
                winner = candidate;
            }
            candidate = getWinner(board[i][0], board[i][1], board[i][2]);
            if (candidate > winner) {
                winner = candidate;
            }
        }

        candidate = getWinner(board[0][0], board[1][1], board[2][2]);
        if (candidate > winner) {
            winner = candidate;
        }

        candidate = getWinner(board[0][2], board[1][1], board[2][0]);
        if (candidate > winner) {
            winner = candidate;
        }

        if (winner == -1) {
            boolean full = true;
            for (int i=0; i<board.length; i++) {
                for (int j=0; j<board[i].length; j++) {
                    if (board[i][j] == 0) {
                        full = false;
                    }
                }
            }

            if (full) {
                return 0;
            }
        }

        return winner;
    }

    private int getWinner(int a, int b, int c) {
        if (a == b && b == c && a != 0) {
            return a;
        } else {
            return -1;
        }
    }

    private int getWinner(int[] row) {
        return getWinner(row[0], row[1], row[2]);
    }
}