package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manages the SQLite database connection using the <b>Singleton Pattern</b>.
 * <p>
 * Ensures only one database connection exists throughout the application lifecycle.
 * On first initialization, it automatically creates the required tables
 * ({@code wallets} and {@code transactions}) if they do not already exist.
 *
 * @author Sarp Onaran
 * @version 1.1
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:flexpay_wallet.db";
    private static DatabaseManager instance;
    private Connection connection;

    /**
     * Private constructor — initializes the SQLite connection and creates tables.
     * Called only once via {@link #getInstance()}.
     */
    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            initializeTables();
            System.out.println("SQLite database connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    /**
     * Returns the singleton instance of DatabaseManager.
     * Creates the instance on first call (lazy initialization).
     *
     * @return the single DatabaseManager instance
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Returns the active database connection.
     *
     * @return the SQLite {@link Connection} object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Creates the wallets and transactions tables if they do not exist.
     *
     * @throws SQLException if a database access error occurs
     */
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

    /**
     * Closes the database connection gracefully.
     * Safe to call multiple times — checks if the connection is already closed.
     */
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
