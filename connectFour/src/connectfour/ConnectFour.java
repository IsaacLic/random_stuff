//Isaac Lichter
package connectfour;

public class ConnectFour {

   
    public static void main(String[] args) {
        connectFourGame game = new connectFourGame();
        game.displayBoard();
        do {
            game.togglePlayer();
            game.promptAndMakeMove();
            game.displayBoard();
        } while (! game.isGameOver());
        System.out.println("The winner is: " + game.getWinner());

    }

}
