/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class GameController {
    private GameView view;
    private GridView color;
    private SudokuBoard model;
    private User user;
    private JFrame parentFrame;
    private int lives;

    public GameController(JFrame parentFrame, User user, GameState gameState) {
        this.parentFrame = parentFrame;
        this.user = user;
        this.view = new GameView();

        DifficultyLevel selectedDifficulty = (gameState != null) ? promptForDifficulty() : null;
        this.model = new SudokuBoard(selectedDifficulty);
        this.lives = (gameState != null) ? gameState.getLivesLeft() : 3;

        view.addSaveListener(this::handleSave);
        view.addRestartListener(this::handleRestart);
        view.addQuitListener(this::handleQuit);
        view.addCellListener(this::handleCellInput);

        if (gameState != null) {
            model.removeNumbersToMatch(gameState.getBlanksCount());
        }
        updateView();
    }

    private DifficultyLevel promptForDifficulty() {
        DifficultyLevel selectedDifficulty = (DifficultyLevel) JOptionPane.showInputDialog(
            parentFrame, "Select difficulty level:", "Difficulty Selection",
            JOptionPane.QUESTION_MESSAGE, null, DifficultyLevel.values(), DifficultyLevel.MEDIUM
        );
        return (selectedDifficulty == null) ? DifficultyLevel.MEDIUM : selectedDifficulty;
    }

    private void handleCellInput(int row, int col, int number) {
        if (model.isValidMove(row, col, number)) {
            model.setNumber(row, col, number);
            color.setCellColor(row, col, true);
            if (model.isComplete()) {
                view.showMessage("Congratulations! You've completed the puzzle.");
                handleRestart();
            }
        } else {
            color.setCellColor(row, col, false);
            lives--;
            updateView();
            if (lives <= 0) {
                view.showMessage("Game Over! You've run out of lives.");
                handleRestart();
            }
        }
    }

    private void handleSave() {
        try {
            user.saveProgress(model.getBlankCount(), lives); //, model.getNumberOfWrongAnswers()
            view.showMessage("Game saved successfully!");
        } catch (SQLException e) {
            view.showMessage("Error saving game: " + e.getMessage());
        }
    }

    private void handleRestart() {
        model.resetBoard();
        lives = 3;
        updateView();
    }

    private void handleQuit() {
        parentFrame.getContentPane().removeAll();
        parentFrame.add(new MainMenuController(parentFrame, user).getView());
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void updateView() {
        view.updateView(model, lives);
    }

    public GameView getView() {
        return view;
    }
}
