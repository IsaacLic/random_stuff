//Isaac Lichter
package tictactoe;

public class TicTacToeMain {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(2);
        do {
            game.displayBoard();
            game.togglePlayer();
            game.promptAndMakeMove();
        } while (! game.isGameOver());
        System.out.println(game.getWinner());

    }

}