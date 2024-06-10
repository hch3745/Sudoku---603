/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kevin
 */
public class GameView extends JPanel {
    private GridView gridView;
    private JLabel livesLabel;
    private JButton saveButton;
    private JButton restartButton;
    private JButton quitButton;

    public GameView() {
        setLayout(new BorderLayout());
        gridView = new GridView();
        livesLabel = new JLabel("Lives: 3");
        saveButton = new JButton("Save");
        restartButton = new JButton("Restart");
        quitButton = new JButton("Quit");

        add(gridView, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        controlPanel.add(livesLabel);
        controlPanel.add(saveButton);
        controlPanel.add(restartButton);
        controlPanel.add(quitButton);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public void updateView(SudokuBoard board, int lives) {
        gridView.updateGrid(board);
        livesLabel.setText("Lives: " + lives);
    }

    public void addSaveListener(Runnable listener) {
        saveButton.addActionListener(e -> listener.run());
    }

    public void addRestartListener(Runnable listener) {
        restartButton.addActionListener(e -> listener.run());
    }

    public void addQuitListener(Runnable listener) {
        quitButton.addActionListener(e -> listener.run());
    }

    public void addCellListener(GameView.CellListener listener) {
        gridView.setCellListener(listener);
    }

    public GridView getGridView() {
        return gridView;
    }

    public interface CellListener {
        void onCellInput(int row, int col, int number);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
