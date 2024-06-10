/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import java.sql.*;

public class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public void saveProgress(int blankAnswers, int livesLeft) throws SQLException {
        Connection conn = DatabaseManager.getConnection();
        conn.setAutoCommit(false);

        try (
            PreparedStatement checkGameStmt = conn.prepareStatement("SELECT * FROM SAVED_GAMES WHERE USERNAME = ?");
            PreparedStatement updateGameStmt = conn.prepareStatement("UPDATE SAVED_GAMES SET BLANK_ANSWERS = ? WHERE USERNAME = ?");
            PreparedStatement insertGameStmt = conn.prepareStatement("INSERT INTO SAVED_GAMES (USERNAME, BLANK_ANSWERS) VALUES (?, ?)");
            PreparedStatement checkLivesStmt = conn.prepareStatement("SELECT * FROM LIVES WHERE USERNAME = ?");
            PreparedStatement updateLivesStmt = conn.prepareStatement("UPDATE LIVES SET LIVES_LEFT = ? WHERE USERNAME = ?");
            PreparedStatement insertLivesStmt = conn.prepareStatement("INSERT INTO LIVES (USERNAME, LIVES_LEFT) VALUES (?, ?)")
        ) {
            // Update or insert game progress
            checkGameStmt.setString(1, username);
            ResultSet gameRs = checkGameStmt.executeQuery();
            if (gameRs.next()) {
                updateGameStmt.setInt(1, blankAnswers);
                updateGameStmt.setString(2, username);
                updateGameStmt.executeUpdate();
            } else {
                insertGameStmt.setString(1, username);
                insertGameStmt.setInt(2, blankAnswers);
                insertGameStmt.executeUpdate();
            }
            gameRs.close();

            // Update or insert lives
            checkLivesStmt.setString(1, username);
            ResultSet livesRs = checkLivesStmt.executeQuery();
            if (livesRs.next()) {
                updateLivesStmt.setInt(1, livesLeft);
                updateLivesStmt.setString(2, username);
                updateLivesStmt.executeUpdate();
            } else {
                insertLivesStmt.setString(1, username);
                insertLivesStmt.setInt(2, livesLeft);
                insertLivesStmt.executeUpdate();
            }
            livesRs.close();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public GameState loadProgress() throws SQLException {
        try (
            PreparedStatement pstmt1 = DatabaseManager.getConnection().prepareStatement("SELECT BLANK_ANSWERS FROM SAVED_GAMES WHERE USERNAME = ?");
            PreparedStatement pstmt2 = DatabaseManager.getConnection().prepareStatement("SELECT LIVES_LEFT FROM LIVES WHERE USERNAME = ?")
        ) {
            pstmt1.setString(1, username);
            ResultSet rs1 = pstmt1.executeQuery();

            pstmt2.setString(1, username);
            ResultSet rs2 = pstmt2.executeQuery();

            if (rs1.next() && rs2.next()) {
                int blankAnswers = rs1.getInt("BLANK_ANSWERS");
                int livesLeft = rs2.getInt("LIVES_LEFT");
                return new GameState(blankAnswers, livesLeft);
            }
        }
        return null;
    }
}
