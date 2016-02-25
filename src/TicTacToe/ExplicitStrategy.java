package tictactoe;

/**
 * Created by Usiel on 23.02.2016.
 */
public class ExplicitStrategy implements IStrategy {
    @Override
    public Move getBestMove(int[][] board, int playerId) {
        Move m = null;
        BoardAnalyzer analyzer = new BoardAnalyzer();

        //winning move
        m = analyzer.getWinningMove(board, playerId);
        if (m != null) {
            return m;
        }

        //blocking winning move
        m = analyzer.getWinBlockingMove(board, playerId);
        if (m != null) {
            return m;
        }

        //create 2 winning moves for next round
        m = analyzer.getTwoWinMovesMove(board, playerId);
        if (m != null ){
            return m;
        }

        //block 2 winning moves for next round
        m = analyzer.getTwoWinMovesBlockingMove(board, playerId);
        if (m != null) {
            return m;
        }

        //create winning move for next round
        m = analyzer.getWinningMoveForNextRoundMove(board, playerId);
        if (m != null) {
            return m;
        }

        //place in middle
        m = analyzer.getMiddleMove(board, playerId);
        if (m != null) {
            return m;
        }

        return new RandomStrategy().getBestMove(board, playerId);
    }
}
