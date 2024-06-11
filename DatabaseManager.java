/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:derby:SudokuDB_Ebd; create=true";
    private static final String USER = "sud";
    private static final String PASS = "sud";
    private static Connection conn;

    public static void initializeDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseManager dbi = new DatabaseManager();
        System.out.println(dbi.getConnection());
    }

    private static void createTables() throws SQLException {
        try ( Statement stmt = conn.createStatement()) {
            createAccountTable(stmt);
            createSavedGamesTable(stmt);
            createLivesTable(stmt);
        }
    }

    private static void createAccountTable(Statement stmt) throws SQLException {
        if (!tableExists("ACCOUNTS")) {
            stmt.execute("CREATE TABLE ACCOUNTS (USERNAME VARCHAR(50) PRIMARY KEY, PASSWORD VARCHAR(255))");
        }
    }

    /*public static void dropAndRecreateTable(String tableName) {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement()) {
        
        // Drop the table if it exists
        stmt.execute("DROP TABLE " + tableName);
        System.out.println("Table " + tableName + " dropped successfully.");
        
        // Recreate the table
        switch (tableName.toUpperCase()) {
            case "SAVED_GAMES":
                createSavedGamesTable(stmt);
                break;
            // Add cases for other tables if needed
            default:
                System.out.println("Unknown table: " + tableName);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private static void createSavedGamesTable(Statement stmt) throws SQLException {
    stmt.execute("CREATE TABLE SAVED_GAMES (USERNAME VARCHAR(50) PRIMARY KEY, BLANK_ANSWERS INT, DIFFICULTY VARCHAR(50))");
    System.out.println("Table SAVED_GAMES created successfully.");
}*/
    
    private static void createSavedGamesTable(Statement stmt) throws SQLException {
        if (!tableExists("SAVED_GAMES")) {
            stmt.execute("CREATE TABLE SAVED_GAMES (USERNAME VARCHAR(50) PRIMARY KEY, BLANK_ANSWERS INT, DIFFICULTY VARCHAR(50))");
        }
    }

    private static void createLivesTable(Statement stmt) throws SQLException {
        if (!tableExists("LIVES")) {
            stmt.execute("CREATE TABLE LIVES (USERNAME VARCHAR(50), LIVES_LEFT INT, FOREIGN KEY (USERNAME) REFERENCES ACCOUNTS(USERNAME))");
        }
    }

    private static boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        try ( ResultSet rs = meta.getTables(null, null, tableName.toUpperCase(), null)) {
            return rs.next();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
