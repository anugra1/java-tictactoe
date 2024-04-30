import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TicTacToeGUI extends JFrame {
    private JButton[][] buttons;
    private char[][] board;
    private char currentPlayer;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        setSize(300, 300);

        buttons = new JButton[3][3];
        board = new char[3][3];
        currentPlayer = 'X';

        initializeBoard();
    }
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50)); 
                buttons[i][j].setBackground(Color.blue);
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (board[row][col] == ' ' && currentPlayer == 'X') {
                            board[row][col] = currentPlayer;
                            buttons[row][col].setText(Character.toString(currentPlayer));
                            if (checkWin(currentPlayer)) {
                                JOptionPane.showMessageDialog(null, currentPlayer + " wins!");
                                resetBoard();
                            } else if (checkDraw()) {
                                JOptionPane.showMessageDialog(null, "It's a draw!");
                                resetBoard();
                            } else {
                                currentPlayer = 'O';
                                aiMakeMove();
                            }
                        }
                    }
                });
                add(buttons[i][j]);
            }
        }
    }    
    private void aiMakeMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');
        board[row][col] = 'O';
        buttons[row][col].setText("O"); // Set the button text to "O"
        currentPlayer = 'X';
        if (checkWin('O')) {
            JOptionPane.showMessageDialog(null, "AI wins!");
            resetBoard();
        } else if (checkDraw()) {
            JOptionPane.showMessageDialog(null, "It's a draw!");
            resetBoard();
        }
    }
     private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGUI().setVisible(true);
            }
        });
    }
}