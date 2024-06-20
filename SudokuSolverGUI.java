import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverGUI extends JFrame {
    private JTextField[][] cells;
    private static final int GRID_SIZE = 9;

    public SudokuSolverGUI() {
        setTitle("Sudoku Solver");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cells = new JTextField[GRID_SIZE][GRID_SIZE];
        JPanel panel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                panel.add(cells[row][col]);
            }
        }

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void solveSudoku() {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                String text = cells[row][col].getText();
                if (!text.isEmpty()) {
                    board[row][col] = Integer.parseInt(text);
                } else {
                    board[row][col] = 0;
                }
            }
        }

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            updateBoard(board);
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists for the given Sudoku puzzle", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setText(String.valueOf(board[row][col]));
            }
        }
    }
}
