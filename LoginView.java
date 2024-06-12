/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import javax.swing.*;
import java.awt.*;


public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton loginButton;

    public LoginView() {
        setTitle("Log In");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        add(registerButton);
        add(loginButton);
    }
    
    // Getter and setter methods for username and password
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    
    // Event listener methods for register and login
    public void addRegisterListener(Runnable listener) {
        registerButton.addActionListener(e -> listener.run());
    }

    public void addLoginListener(Runnable listener) {
        loginButton.addActionListener(e -> listener.run());
    }
    
    // Shows a message dialog
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
