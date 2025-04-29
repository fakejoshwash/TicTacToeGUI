import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    private final JButton[][] buttons;
    private String currentPlayer = "X";
    private int yn;
    public TicTacToeFrame() {
        super("Tic Tac Toe!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        initializeButtons();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton("");
                button.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
                buttons[row][col] = button;
                final int finalRow = row + 1;
                final int finalCol = col + 1;
                button.addActionListener(e -> {
                    if (((JButton) e.getSource()).getText().isEmpty()) {
                        if (TicTacToe.makeMove(finalRow, finalCol, currentPlayer)) {
                            ((JButton)e.getSource()).setText(currentPlayer);
                            if (TicTacToe.isWin(currentPlayer)) {
                                yn = JOptionPane.showConfirmDialog(null, "PLAYER " + currentPlayer + " WINS!!!\n\nPlay again?", "Result", javax.swing.JOptionPane.YES_NO_OPTION);
                                if (yn == 1) {
                                    System.exit(0);
                                }
                                reset();
                            } else if (isBoardFull()) {
                                yn = JOptionPane.showConfirmDialog(null, "Tie!\n\nPlay again?", "Result", javax.swing.JOptionPane.YES_NO_OPTION);
                                if (yn == 1) {
                                    System.exit(0);
                                }
                                reset();
                            } else {
                                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                            }
                        }
                    }
                });
                add(button);
            }
        }
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }

        TicTacToe.clearBoard();
        currentPlayer = "X";
    }

}
