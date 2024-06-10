/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import javax.swing.*;
import java.awt.*;

public class SudokuApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            DatabaseManager.initializeDatabase();

            JFrame frame = new JFrame("Sudoku Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setMinimumSize(new Dimension(600, 400));

            LoginController loginController = new LoginController(frame);
            loginController.showLoginView();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    DatabaseManager.disconnect();
                }
            });
        });
    }
}
