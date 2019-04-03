//Isaac Lichter
package othellomain;

import java.util.Scanner;

enum Cell {
    NONE, O, X
};

public class OthelloBoard {

    private boolean isPlayerOneMove;
    private boolean isWinner;
    private boolean isPlayerXAbleToMove;
    private boolean isPlayerYAbleToMove;
    private Cell[][] grid;

    public OthelloBoard() {

        grid = new Cell[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                Cell cell = Cell.NONE;
                grid[row][col] = cell;
            }
        }
        Cell cell = Cell.X;
        grid[3][3] = cell;
        grid[4][4] = cell;

        cell = Cell.O;
        grid[3][4] = cell;
        grid[4][3] = cell;

        isWinner = false;
        isPlayerOneMove = true;
        isPlayerXAbleToMove = true;
        isPlayerYAbleToMove = true;

    }

    public void playTwoPlayer() {

        do {
            display(grid);
            promptAndMakeMove();
        } while (!isGameOver());
    }

    void playComputer() {
        OthelloComputer computer = new OthelloComputer();
        do {
            display(grid);
            if (isPlayerOneMove) {
                promptAndMakeMove();
            } else {
                computer.makeMove();
            }
        } while (!isGameOver());
    }

    private class OthelloComputer {

        private Cell[][][] tempBoard;
        Cell computerPlayerCell;
        private Cell currentHypotheticalPlayer;

        private void toggleHypotheticalPlayer() {
            currentHypotheticalPlayer = (currentHypotheticalPlayer == Cell.X ? Cell.O : Cell.X);
        }

        private void copyBoard(Cell[][] copySource, Cell[][] copyDestination) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    copyDestination[row][col] = copySource[row][col];
                }
            }
        }

        public void makeMove() {
            computerPlayerCell = isPlayerOneMove ? Cell.X : Cell.O;
            currentHypotheticalPlayer = computerPlayerCell;
            double bestMove = 0;
            int bestRow = 0;
            int bestCol = 0;
            tempBoard = new Cell[8][8][8];
            System.out.println("");
            for (int x = 0; x < 8; x++) {
                copyBoard(grid, tempBoard[x]);
            }
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (isValidMove(row, col, tempBoard[7], computerPlayerCell)) {
                        copyBoard(tempBoard[7], tempBoard[6]);
                        double thisMove = recursiveTryMove(7);
                        copyBoard(grid, tempBoard[7]);
                        if (thisMove > bestMove) {
                            bestMove = thisMove;
                            bestRow = row;
                            bestCol = col;
                        }
                    }
                }
            }
            System.out.println("Here's my move:");
            System.out.println("");
            FlipIfLegal(bestRow, bestCol, grid, computerPlayerCell);
            isPlayerOneMove = !isPlayerOneMove;
            currentHypotheticalPlayer = Cell.NONE;
        }

        private double recursiveTryMove(int iterations) {
            if (iterations == 0 || iterations == 1) {
//                display(tempBoard[iterations]);
                return isPlayerOneMove ? getXValue() : getOValue();
            }
            double sumOfMoveValues = 0;
            int validMoveCount = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (isValidMove(row, col, tempBoard[iterations], currentHypotheticalPlayer)) {
                        validMoveCount++;
                        toggleHypotheticalPlayer();
                        copyBoard(tempBoard[iterations - 1], tempBoard[iterations - 2]);
                        double thisMove = recursiveTryMove(iterations - 1);
                        copyBoard(tempBoard[iterations], tempBoard[iterations - 1]);
                        toggleHypotheticalPlayer();
                        sumOfMoveValues += thisMove;
                        validMoveCount++;
                    }
                }
            }
            return sumOfMoveValues / validMoveCount; //average of all possibilities in the tree
        }

        private int getXValue() {
            int sum = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (tempBoard[0][row][col] == Cell.X) {

                        sum++;

                        if (row == 0) {
                            if (col == 0 || col == 7) {
                                sum += 6;
                            }
                        }

                        if (row == 7) {
                            if (col == 0 || col == 7) {
                                sum += 6;
                            }
                        }

                    }
                }
            }

            return sum;
        }

        private int getOValue() {
            int sum = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (tempBoard[0][row][col] == Cell.O) {
                        sum++;

                        if (row == 0) {
                            if (col == 0 || col == 7) {
                                sum += 6;
                            }
                        }

                        if (row == 7) {
                            if (col == 0 || col == 7) {
                                sum += 6;
                            }
                        }

                    }
                }
            }
            return sum;
        }

    }

    private void promptAndMakeMove() {
        try {
            Scanner kb = new Scanner(System.in);
            int col, row;
            do {
                System.out.println("Enter your Move (A1 - H8), player "
                        + (isPlayerOneMove ? "X" : "O"));
                String userMove = kb.next();
                col = userMove.charAt(0) - 'A';
                row = userMove.charAt(1) - '1';
                System.out.println("");
            } while (!isValidMove(row, col, grid, isPlayerOneMove ? Cell.X : Cell.O));
            togglePlayer();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("That was not a position.");
            promptAndMakeMove();
        }
    }

    private boolean isValidMove(int row, int col, Cell[][] board, Cell activePlayer) {
        if (row >= 0 && row <= 7 && col >= 0 && col <= 7) {
            Cell cell = Cell.NONE;
            if (board[row][col] == cell) {
                if (FlipIfLegal(row, col, board, activePlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void display(Cell[][] board) {
        System.out.println("  A B C D E F G H");
        for (int row = 0; row < 8; row++) {
            System.out.print((row + 1) + ":");
            for (int col = 0; col < 8; col++) {
                System.out.print(convertToString(board[row][col]) + " ");
            }
            System.out.println("");
        }

    }

    private void togglePlayer() {
        isPlayerOneMove = !isPlayerOneMove;
    }

    private boolean isGameOver() {
//        if (!isPlayerXAbleToMove && !isPlayerYAbleToMove) {
//            return true;
//        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = Cell.NONE;
                if (grid[row][col] == cell) {
                    return false;
                }
            }
        }
        System.out.println("The winner is " + getWinner());
        return true;
    }

    private int xPoints() {
        int points = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = Cell.X;
                if (grid[row][col] == cell) {
                    points++;
                }
            }
        }
        return points;
    }

    private int oPoints() {
        int points = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = Cell.O;
                if (grid[row][col] == cell) {
                    points++;
                }
            }
        }
        return points;
    }

    private Cell getWinner() {

        Cell cell;
        if (xPoints() > oPoints()) {

            cell = Cell.X;
        } else if (oPoints() > xPoints()) {

            cell = Cell.O;
        } else {
            cell = Cell.NONE;
        }

        return cell;
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

    private boolean FlipIfLegal(int row, int col, Cell[][] board, Cell activePlayer) {

        Cell cell = activePlayer;
        board[row][col] = cell;

        boolean isUR = isUpperRightFlip(row, col, board);
        boolean isU = isUpperFlip(row, col, board);
        boolean isUL = isUpperLeftFlip(row, col, board);
        boolean isL = isLeftFlip(row, col, board);
        boolean isBL = isBottomLeftFlip(row, col, board);
        boolean isB = isBottomFlip(row, col, board);
        boolean isBR = isBottomRightFlip(row, col, board);
        boolean isR = isRightFlip(row, col, board);

        if (isUR || isU || isUL || isL || isBL || isB || isBR || isR) {
            return true;
        }

        cell = Cell.NONE;
        board[row][col] = cell;
        return false;

    }

    private boolean isUpperRightFlip(int row, int col, Cell[][] board) {
        Cell cell = Cell.NONE;
        if (row - 1 < 0 || col + 1 > 7 || board[row - 1][col + 1] == cell || board[row - 1][col + 1] == board[row][col]) {
            return false;
        }
        int newRow = row;
        int newCol = col;
        do {
            newRow--;
            newCol++;
            if (board[newRow][newCol] == board[row][col]) {
                do {
                    board[newRow][newCol] = board[row][col];
                    newRow++;
                    newCol--;
                } while (newRow != row);
                return true;
            }
        } while (newRow > 0 && newCol < 7 && board[newRow][newCol] != cell);
        return false;

    }

    private boolean isUpperFlip(int row, int col, Cell[][] board) {
        Cell cell = Cell.NONE;
        if (row - 1 < 0 || board[row - 1][col] == cell || board[row - 1][col] == board[row][col]) {
            return false;
        }
        int newRow = row;
        do {
            newRow--;
            if (board[newRow][col] == board[row][col]) {
                do {
                    board[newRow][col] = board[row][col];
                    newRow++;
                } while (newRow != row);
                return true;
            }
        } while (newRow > 0 && board[newRow][col] != cell);
        return false;

    }

    private boolean isUpperLeftFlip(int row, int col, Cell[][] board) {
        Cell cell = Cell.NONE;
        if (row - 1 < 0 || col - 1 < 0 || board[row - 1][col - 1] == cell || board[row - 1][col - 1] == board[row][col]) {
            return false;
        }
        int newRow = row;
        int newCol = col;
        do {
            newRow--;
            newCol--;
            if (board[newRow][newCol] == board[row][col]) {
                do {
                    board[newRow][newCol] = board[row][col];
                    newRow++;
                    newCol++;
                } while (newRow != row);
                return true;
            }
        } while (newRow > 0 && newCol > 0 && board[newRow][newCol] != cell);
        return false;

    }

    private boolean isLeftFlip(int row, int col, Cell[][] board) {
        Cell cell = Cell.NONE;
        if (col - 1 < 0 || board[row][col - 1] == cell || board[row][col - 1] == board[row][col]) {
            return false;
        }
        int newCol = col;
        do {
            newCol--;
            if (board[row][newCol] == board[row][col]) {
                do {
                    board[row][newCol] = board[row][col];
                    newCol++;
                } while (newCol != col);
                return true;
            }
        } while (newCol > 0 && board[row][newCol] != cell);
        return false;

    }

    private boolean isBottomLeftFlip(int row, int col, Cell[][] board) {
        Cell cell = Cell.NONE;
        if (row + 1 > 7 || col - 1 < 0 || board[row + 1][col - 1] == cell || board[row + 1][col - 1] == board[row][col]) {
            return false;
        }
        int newRow = row;
        int newCol = col;
        do {
            newRow++;
            newCol--;
            if (board[newRow][newCol] == board[row][col]) {
                do {
                    board[newRow][newCol] = board[row][col];
                    newRow--;
                    newCol++;
                } while (newRow != row);
                return true;
            }
        } while (newRow < 7 && newCol > 0 && board[newRow][newCol] != cell);
        return false;

    }

    private boolean isBottomFlip(int row, int col, Cell[][] board) {
        Cell cell = Cell.NONE;
        if (row + 1 > 7 || board[row + 1][col] == cell || board[row + 1][col] == board[row][col]) {
            return false;
        }
        int newRow = row;
        do {
            newRow++;
            if (board[newRow][col] == board[row][col]) {
                do {
                    board[newRow][col] = board[row][col];
                    newRow--;
                } while (newRow != row);
                return true;
            }
        } while (newRow < 7 && board[newRow][col] != cell);
        return false;

    }

    private boolean isBottomRightFlip(int row, int col, Cell[][] board) {
        Cell cell = Cell.NONE;
        if (row + 1 > 7 || col + 1 > 7 || board[row + 1][col + 1] == cell || board[row + 1][col + 1] == board[row][col]) {
            return false;
        }
        int newRow = row;
        int newCol = col;
        do {
            newRow++;
            newCol++;
            if (board[newRow][newCol] == board[row][col]) {
                do {
                    board[newRow][newCol] = board[row][col];
                    newRow--;
                    newCol--;
                } while (newRow != row);
                return true;
            }
        } while (newRow < 7 && newCol < 7 && board[newRow][newCol] != cell);
        return false;

    }

    private boolean isRightFlip(int row, int col, Cell[][] board) {
        Cell cell = Cell.NONE;
        if (col + 1 > 7 || board[row][col + 1] == cell || board[row][col + 1] == board[row][col]) {
            return false;
        }
        int newCol = col;
        do {
            newCol++;
            if (board[row][newCol] == board[row][col]) {
                do {
                    board[row][newCol] = board[row][col];
                    newCol--;
                } while (newCol != col);
                return true;
            }
        } while (newCol < 7 && board[row][newCol] != cell);
        return false;

    }

}
