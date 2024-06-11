/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class GridView extends JPanel {

    private SudokuCellView[][] cells;
    private GameView.CellListener cellListener;

    public GridView() {
        setLayout(new GridLayout(9, 9, 2, 2));
        cells = new SudokuCellView[9][9];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new SudokuCellView();
                final int r = row, c = col;
                cells[row][col].addActionListener(e -> {
                    String input = cells[r][c].getText();
                    int number = input.isEmpty() ? 0 : Integer.parseInt(input);
                    if (cellListener != null) {
                        cellListener.onCellInput(r, c, number);
                    }
                });
                add(cells[row][col]);
            }
        }
    }

    public void updateGrid(SudokuBoard board) {
        int[][] boardState = board.getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = boardState[row][col];
                cells[row][col].setValue(value, value != 0);
            }
        }
    }

    public void setCellListener(GameView.CellListener listener) {
        this.cellListener = listener;
    }

    public void setCellColor(int row, int col, boolean isCorrect) {
        cells[row][col].setInputColor(isCorrect);
    }
}
