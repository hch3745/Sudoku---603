/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class GameController {

    public GameView view;
    public GridView color;
    public SudokuBoard model;
    public User user;
    public JFrame parentFrame;
    public int lives;

    // Initializes the game controller with the necessary components
    public GameController(JFrame parentFrame, User user, GameState gameState) {
        this.parentFrame = parentFrame;
        this.user = user;
        this.view = new GameView();
        this.color = view.getGridView();

        DifficultyLevel selectedDifficulty;
        if (gameState == null) {
            selectedDifficulty = promptForDifficulty();
        } else {
            selectedDifficulty = DifficultyLevel.CUSTOM;
        }

        this.model = new SudokuBoard(selectedDifficulty);
        this.lives = (gameState != null) ? gameState.getLivesLeft() : 3;

        view.addSaveListener(this::handleSave);
        view.addRestartListener(this::handleRestart);
        view.addQuitListener(this::handleQuit);
        view.addCellListener(this::handleCellInput);

        if (gameState != null) {
            model.removeNumbersToMatch(gameState.getBlankAnswers());
        }
        updateView();
    }

    // Prompts the user to select the difficulty level
    public DifficultyLevel promptForDifficulty() {
        DifficultyLevel selectedDifficulty = (DifficultyLevel) JOptionPane.showInputDialog(
                parentFrame, "Select difficulty level:", "Difficulty Selection",
                JOptionPane.QUESTION_MESSAGE, null, DifficultyLevel.values(), DifficultyLevel.MEDIUM
        );
        return (selectedDifficulty == null) ? DifficultyLevel.MEDIUM : selectedDifficulty;
    }

    // Handles the user input for a cell in the Sudoku grid
    public void handleCellInput(int row, int col, int number) {
        if (number < 1 || number > 9) {
            // Invalid number, decrement lives
            lives--;
            color.setCellColor(row, col, false); // Use color to set cell color
            updateView();
            checkGameOver();
            return;
        }

        if (model.isValidMove(row, col, number)) {
            model.setNumber(row, col, number);
            color.setCellColor(row, col, true); // Use color to set cell color
            if (model.isComplete()) {
                view.showMessage("Congratulations! You've completed the puzzle.");
                handleRestart();
            }
        } else {
            color.setCellColor(row, col, false); // Use color to set cell color
            lives--;
            updateView();
            checkGameOver();
        }
    }

    // Checks if the game is over (no lives left)
    private void checkGameOver() {
        if (lives <= 0) {
            view.showMessage("Game Over! You've run out of lives.");
            // handleRestart() is commented out to prevent unintended resets in tests
            handleRestart();
        }
    }
    
    // Saves the current game progress
    public void handleSave() {
        try {
            user.saveProgress(model.getBlankCount(), lives);
            view.showMessage("Game saved successfully!");
        } catch (SQLException e) {
            view.showMessage("Error saving game: " + e.getMessage());
        }
    }
    
    // Restarts the game
    public void handleRestart() {
        model.resetBoard();
        lives = 3;
        updateView();
    }
    
    // Quits the game and returns to the main menu
    public void handleQuit() {
        parentFrame.getContentPane().removeAll();
        parentFrame.add(new MainMenuController(parentFrame, user).getView());
        parentFrame.revalidate();
        parentFrame.repaint();
    }
    
    // Updates the game view with the current game state
    public void updateView() {
        view.updateView(model, lives);
    }
    
    // Getter and setter methods for the game view
    public GameView getView() {
        return view;
    }
}
