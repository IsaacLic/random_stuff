//Isaac Lichter
package tictactoe;

import java.awt.Point;
import java.util.Scanner;

enum Cell {
    NONE, O, X
};


public class TicTacToe {

    private boolean isPlayerOneMove;
    private Cell[][] grid;

    TicTacToe(int playerCount) {
        grid = new Cell[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = Cell.NONE;
            }
        }
        // isPlayerOneMove = true;
        // TODO implementb one player game
    }

    public void displayBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(convertToString(grid[row][col]));
            }
            System.out.println();
        }
    }

    void setCell(int row, int col, Cell cell) { // package private
        if (grid[row][col] == Cell.NONE) {
            grid[row][col] = cell;
        }
    }

    Point prompt() {
        Scanner kb = new Scanner(System.in);
        int col, row;
        do {
            System.out.println("Enter your Move (A1 - C3, Mr." + (isPlayerOneMove ? "X" : "O"));
//TODO Validation
            String userMove = kb.next();
            col = userMove.charAt(0) - 'A';
            row = userMove.charAt(1) - '1';
        } while (!isValidMove(col, row));
        return new Point(col, row);
    }

    /**
     * Checks that move in on board (0-3) and cell is available
     */
    boolean isValidMove(int col, int row) {
        return false;
        //TODO
    }

    void togglePlayer() {
        isPlayerOneMove = !isPlayerOneMove;
    }

    public void promptAndMakeMove() {
        Point move = prompt();
        setCell(move.y, move.x, isPlayerOneMove ? Cell.X : Cell.O);
    }

    public boolean isGameOver() {
        // diag #1
        if (grid[0][0] != Cell.NONE
                && grid[0][0] == grid[1][1]
                && grid[2][2] == grid[1][1]) {
            return true;
        }

        //TO diag2 , rows, cols, no legal moves
        return false;
    }

    public Cell getWinner() {
        if (isGameOver()) {
            return isPlayerOneMove ? Cell.X : Cell.O;
        }
        return Cell.NONE;
    }

    private String convertToString(Cell cell) {
        switch (cell) {
            case NONE:
                return " ";
            case O:
                return "O";
            case X:
                return "X";
            default:
                throw new RuntimeException(cell.toString());
        }
    }

}
