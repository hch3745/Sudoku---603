/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import javax.swing.JFrame;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class MainMenuController {

    private MainMenuView view;
    private User user;
    private JFrame parentFrame;

    public MainMenuController(JFrame parentFrame, User user) {
        this.parentFrame = parentFrame;
        this.user = user;
        this.view = new MainMenuView();

        view.addNewGameListener(this::handleNewGame);
        view.addLoadGameListener(this::handleLoadGame);
        view.addLogoutListener(this::handleLogout);
        view.addQuitListener(this::handleQuit);
    }

    private void handleNewGame() {
        parentFrame.getContentPane().removeAll();
        parentFrame.add(new GameController(parentFrame, user, null).getView());
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void handleLoadGame() {
        try {
            GameState gameState = user.loadProgress();
            if (gameState != null) {
                parentFrame.getContentPane().removeAll();
                parentFrame.add(new GameController(parentFrame, user, gameState).getView());
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                view.showMessage("No saved game found.");
            }
        } catch (SQLException e) {
            view.showMessage("Error loading game: " + e.getMessage());
        }
    }

    private void handleLogout() {
        parentFrame.getContentPane().removeAll();
        new LoginController(parentFrame).showLoginView();
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void handleQuit() {
        DatabaseManager.disconnect();
        System.exit(0);
    }

    public MainMenuView getView() {
        return view;
    }
}
