package tictactoe;

public class Player {
    public int id;
    private BoardAnalyzer analyzer;
    private IStrategy strategy;

    public Player(int id, IStrategy strategy) {
        this.id = id;
        this.strategy = strategy;
        analyzer = new BoardAnalyzer();
    }

    public static int[][] copy(int[][] a) {
        int[][] c = new int[a.length][a.length];
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a[i].length; j++) {
                c[i][j] = a[i][j];
            }
        }
        return c;
    }

    public Move move(int[][] board) {
        return strategy.getBestMove(copy(board), this.id);
    }

    public static int alternatePlayerId(int playerId) {
        return (playerId % 2) + 1;
    }
}
