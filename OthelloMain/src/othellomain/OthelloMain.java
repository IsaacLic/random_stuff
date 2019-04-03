//Isaac Lichter
package othellomain;

import java.util.Scanner;

public class OthelloMain {

    public static void main(String[] args) {
        OthelloBoard game = new OthelloBoard();
        Scanner kb = new Scanner(System.in);
        System.out.println("Would you like to play a 1 player game? Or 2?");
        String input = kb.next();
        while(input.charAt(0) != '1' && input.charAt(0) != '2'){
            input = kb.next();
        }
        if (input.charAt(0) == '2')
            game.playTwoPlayer();
        else
            game.playComputer();
    }

}
