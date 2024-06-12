/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserManager {

    // Adds a new user to the database
    public void addUser(String username, String password) throws SQLException {
        String hashedPassword = hashPassword(password);
        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement("INSERT INTO ACCOUNTS VALUES (?, ?)")) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
        }
    }

    // Authenticates a user with the provided credentials
    public boolean authenticateUser(String username, String password) throws SQLException {
        String hashedPassword = hashPassword(password);
        try ( PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement("SELECT * FROM ACCOUNTS WHERE USERNAME = ? AND PASSWORD = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
