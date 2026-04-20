package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:chippin_wallet.db";
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            initializeTables();
            System.out.println("SQLite database connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void initializeTables() throws SQLException {
        Statement stmt = connection.createStatement();

        // Wallet table
        stmt.execute("CREATE TABLE IF NOT EXISTS wallets (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "owner_name TEXT NOT NULL, " +
                "credit_card_limit REAL DEFAULT 0, " +
                "food_balance REAL DEFAULT 0, " +
                "chip_points REAL DEFAULT 0, " +
                "is_micro_saving_enabled INTEGER DEFAULT 1" +
                ")");

        // Transaction history table
        stmt.execute("CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "wallet_id INTEGER, " +
                "category TEXT, " +
                "amount REAL, " +
                "timestamp TEXT DEFAULT (datetime('now')), " +
                "FOREIGN KEY(wallet_id) REFERENCES wallets(id)" +
                ")");

        stmt.close();
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
