/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author kevin
 */
public class MainMenuView extends JPanel {

    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton logoutButton;
    private JButton quitButton;

    public MainMenuView() {
        setLayout(new GridLayout(4, 1));
        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load Game");
        logoutButton = new JButton("Logout");
        quitButton = new JButton("Quit");

        add(newGameButton);
        add(loadGameButton);
        add(logoutButton);
        add(quitButton);
    }

    public void addNewGameListener(Runnable listener) {
        newGameButton.addActionListener(e -> listener.run());
    }

    public void addLoadGameListener(Runnable listener) {
        loadGameButton.addActionListener(e -> listener.run());
    }

    public void addLogoutListener(Runnable listener) {
        logoutButton.addActionListener(e -> listener.run());
    }

    public void addQuitListener(Runnable listener) {
        quitButton.addActionListener(e -> listener.run());
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
