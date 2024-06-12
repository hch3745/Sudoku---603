
package sud;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:derby:SudokuDB_Ebd; create=true";
    private static final String USER = "sud";
    private static final String PASS = "sud";
    private static Connection conn;
    
    // Connects to the database and creates the required table
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
    
    // Creates the ACCOUNTS, SAVED_GAMES, and LIVES tables if they don't exist
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

    private static void createSavedGamesTable(Statement stmt) throws SQLException {
        if (!tableExists("SAVED_GAMES")) {
            stmt.execute("CREATE TABLE SAVED_GAMES (USERNAME VARCHAR(50) PRIMARY KEY, BLANK_ANSWERS INT)");
        }
    }

    private static void createLivesTable(Statement stmt) throws SQLException {
        if (!tableExists("LIVES")) {
            stmt.execute("CREATE TABLE LIVES (USERNAME VARCHAR(50), LIVES_LEFT INT, FOREIGN KEY (USERNAME) REFERENCES ACCOUNTS(USERNAME))");
        }
    }
    
    // Checks if a table with the given name exists in the database
    private static boolean tableExists(String tableName) throws SQLException { 
        DatabaseMetaData meta = conn.getMetaData();
        try ( ResultSet rs = meta.getTables(null, null, tableName.toUpperCase(), null)) {
            return rs.next();
        }
    }
    
    // Getter and setter methods for the database connection
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
