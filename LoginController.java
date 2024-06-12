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
public class LoginController {

    private LoginView view;
    private UserManager userManager;
    private JFrame parentFrame;

    public LoginController(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.userManager = new UserManager();
    }
    
    // Shows the login view
    public void showLoginView() {
        view = new LoginView();
        view.setVisible(true);

        view.addRegisterListener(this::handleRegister);
        view.addLoginListener(this::handleLogin);
    }
    
    // Handles user registration
    private void handleRegister() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            view.showMessage("Username and password cannot be empty.");
            return;
        }

        try {
            userManager.addUser(username, password);
            view.showMessage("User registered successfully!");
        } catch (SQLException e) {
            view.showMessage("Error registering user: " + e.getMessage());
        }
    }
    
    // Handles user login
    private void handleLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        try {
            if (userManager.authenticateUser(username, password)) {
                User user = new User(username);
                view.dispose();
                showMainMenu(user);
            } else {
                view.showMessage("Invalid username or password");
            }
        } catch (SQLException e) {
            view.showMessage("Error logging in: " + e.getMessage());
        }
    }
    
    // Shows the main menu after successful login
    private void showMainMenu(User user) {
        MainMenuController mainMenuController = new MainMenuController(parentFrame, user);
        parentFrame.getContentPane().removeAll();
        parentFrame.add(mainMenuController.getView());
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
