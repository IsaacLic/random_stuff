//Isaac Lichter
package tictactoe2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

enum Cell {
    NONE, O, X
};

public class TicTacToeDisplay extends JFrame {

//    private TicTacToeModel gameModel;
    private JButton[][] gridOfButtons = new JButton[3][3];
    private JLabel message;
    private boolean isWinner = false;
    private boolean isPlayerOneMove = false;

    public TicTacToeDisplay() {

        message = new JLabel(" ");

        this.setSize(500, 200);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        TicTacToePanel panel = new TicTacToePanel();
        this.add(panel);
        add(message, BorderLayout.SOUTH);

        setVisible(true);
    }

    class TicTacToePanel extends JPanel {

        private JButton[][] gridButtons = new JButton[3][3];

        public TicTacToePanel() {
            setLayout(new GridLayout(3, 3));
            ClickButtonEventHandler buttonEventHandler = new ClickButtonEventHandler();
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    gridButtons[row][col] = new JButton();
                    add(gridButtons[row][col]);

                    gridButtons[row][col].addActionListener(buttonEventHandler);
                }
            }
        }
    }

    public class ClickButtonEventHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int row = 0, col = 0;
            gridLocationFinder:
            for (row = 0; row < 3; row++) {
                for (col = 0; col < 3; col++) {
                    if (e.getSource() == gridOfButtons[row][col]) {
                        break gridLocationFinder;
                    }
                }
            }
            setCell(row, col, currentPlayer());
            JButton buttonPressed = (JButton) e.getSource();
            buttonPressed.setText(currentPlayer().toString());
            buttonPressed.setEnabled(false);

            message.setText(currentStateMessage());
            if (isGameOver()) {
                endGame();
            } else {
                togglePlayer();
            }

        }
    }

    void endGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gridOfButtons[row][col].setText("");
                gridOfButtons[row][col].setEnabled(true);
                isPlayerOneMove = false;
            }
        }
    }

    boolean setCell(int row, int col, Cell cell) {
        if (gridOfButtons[row][col].getText() == "") {
            gridOfButtons[row][col].setText(convertToString(cell));
            return true;
        }
        return false;
    }

    public boolean isGameOver() {
        if ((gridOfButtons[0][0].getText() != ""
                && gridOfButtons[0][0].getText() == gridOfButtons[1][1].getText()
                && gridOfButtons[2][2].getText() == gridOfButtons[1][1].getText())
                || (gridOfButtons[2][0].getText() != ""
                && gridOfButtons[2][0].getText() == gridOfButtons[1][1].getText()
                && gridOfButtons[0][2].getText() == gridOfButtons[1][1].getText())) {
            this.isWinner = true;
            return true;
        }
        for (int row = 0; row < 3; row++) {
            if (gridOfButtons[row][0].getText() != ""
                    && gridOfButtons[row][0].getText() == gridOfButtons[row][1].getText()
                    && gridOfButtons[row][0].getText() == gridOfButtons[row][2].getText()) {
                this.isWinner = true;
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (gridOfButtons[col][0].getText() != ""
                    && gridOfButtons[0][col].getText() == gridOfButtons[1][col].getText()
                    && gridOfButtons[0][col].getText() == gridOfButtons[2][col].getText()) {
                this.isWinner = true;
                return true;
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gridOfButtons[row][col].getText() == "") {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell getWinner() {
        if (isWinner) {
            return currentPlayer();
        }
        return Cell.NONE;
    }

    String currentStateMessage() {
        if (isGameOver()) {
            return (isPlayerOneMove ? "X" : "O") + " won!";
        }
        return "your turn, " + (isPlayerOneMove ? "O" : "X");
    }

    public Cell currentPlayer() {
        return isPlayerOneMove ? Cell.X : Cell.O;
    }

    void togglePlayer() {
        isPlayerOneMove = !isPlayerOneMove;
    }

    public String convertToString(Cell cell) {
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
