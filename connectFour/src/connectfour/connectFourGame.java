//Isaac Lichter
package connectfour;

import java.awt.Point;
import java.util.Scanner;

enum Cell {
    NONE, O, X
};

class connectFourGame {

    private boolean isPlayerOneMove;
    private boolean isWinner = false;
    private Cell[][] grid;

    connectFourGame() {
        grid = new Cell[6][7];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                grid[row][col] = Cell.NONE;
            }
        }
        isPlayerOneMove = false;
    }

    public void displayBoard() {
        for (int x = 0; x < 7; x++) {
            System.out.print("  " + (char) ('A' + x));
        }
        System.out.println();
        for (int row = 0; row < 6; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < 7; col++) {
                System.out.print(convertToString(grid[row][col]) + " ");
            }
            System.out.println();
        }
    }

    void setCell(int col, Cell cell) {
        for (int row = 5; row >= 0; row--) {
            if (grid[row][col] == Cell.NONE) {
                grid[row][col] = cell;
                return;
            }
        }
    }

    int getMove() {
        Scanner kb = new Scanner(System.in);
        int col;
        do {
            System.out.println("Enter your Move (A - G), Mr. "
                    + (isPlayerOneMove ? "X" : "O"));
            String userMove = kb.next();
            col = userMove.charAt(0) - 'A';
        } while (!isValidMove(col));
        return col;
    }

    boolean isValidMove(int col) {
        for (int row = 5; row >= 0; row--) {
            if (col >= 0 && col <= 6 && grid[row][col] == Cell.NONE) {
                return true;
            }
        }
        return false;
    }

    void togglePlayer() {
        isPlayerOneMove = !isPlayerOneMove;
    }

    public void promptAndMakeMove() {
        int col = getMove();
        setCell(col, isPlayerOneMove ? Cell.X : Cell.O);
    }

    public boolean isGameOver() {

        for (int row = 5; row >= 0; row--) {
            for (int col = 6; col >= 0; col--) {
                if (grid[row][col] != Cell.NONE
                        && isFourInARow(row, col) == true) {
                    return true;
                }
            }
        }
        for (int row = 5; row >= 0; row--) {
            for (int col = 6; col >= 0; col--) {
                if (grid[row][col] == Cell.NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell getWinner() {
        if (isWinner) {
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

    private boolean isFourInARow(int row, int col) {
        if (row >= 3) {
            if (col <= 3) {
                if (isVerticalWIn(row, col) || isHorizontalWin(row, col)
                        || isRightDiagonalWin(row, col)) {
                    return true;
                }
            }
            if (col >= 3) {
                if (isVerticalWIn(row, col)
                        || isLeftDiagonalWin(row, col)) {
                    return true;
                }
            }
        } else if (col <= 3) {
            return isHorizontalWin(row, col);
        }

        return false;
    }

    private boolean isVerticalWIn(int row, int col) {
        if (grid[row][col] == grid[row - 1][col]
                && grid[row][col] == grid[row - 2][col]
                && grid[row][col] == grid[row - 3][col]) {
            this.isWinner = true;
            return true;
        }
        return false;
    }

    private boolean isHorizontalWin(int row, int col) {
        if (grid[row][col] == grid[row][col + 1]
                && grid[row][col] == grid[row][col + 2]
                && grid[row][col] == grid[row][col + 3]) {
            this.isWinner = true;
            return true;
        }
        return false;
    }

    private boolean isRightDiagonalWin(int row, int col) {
        if (grid[row][col] == grid[row - 1][col + 1]
                && grid[row][col] == grid[row - 2][col + 2]
                && grid[row][col] == grid[row - 3][col + 3]) {
            this.isWinner = true;
            return true;
        }
        return false;
    }

    private boolean isLeftDiagonalWin(int row, int col) {
        if (grid[row][col] == grid[row - 1][col - 1]
                && grid[row][col] == grid[row - 2][col - 2]
                && grid[row][col] == grid[row - 3][col - 3]) {
            this.isWinner = true;
            return true;
        }
        return false;
    }

}
