package repository;

import java.sql.*;

/**
 * Repository class for logging and querying payment transactions.
 * <p>
 * Every successful payment is recorded in the {@code transactions} table
 * with a timestamp, category, and amount for audit and analytics purposes.
 *
 * @author Sarp Onaran
 * @version 1.0
 */
public class TransactionRepository {
    private final Connection connection;

    /**
     * Constructs a TransactionRepository with the given database connection.
     *
     * @param connection the active SQLite connection
     */
    public TransactionRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Logs a payment transaction to the database.
     *
     * @param walletId the ID of the wallet that made the payment
     * @param category the payment category (e.g., "RESTAURANT", "RETAIL")
     * @param amount   the transaction amount in TL
     * @throws SQLException if a database access error occurs
     */
    public void logTransaction(int walletId, String category, double amount) throws SQLException {
        String sql = "INSERT INTO transactions (wallet_id, category, amount) VALUES (?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, walletId);
        stmt.setString(2, category);
        stmt.setDouble(3, amount);
        stmt.executeUpdate();
        stmt.close();

        System.out.println("📝 Transaction logged: " + category + " | " + amount + " TL");
    }

    /**
     * Prints the complete transaction history for a given wallet to the console.
     * Transactions are displayed in reverse chronological order.
     *
     * @param walletId the ID of the wallet to query
     * @throws SQLException if a database access error occurs
     */
    public void printTransactionHistory(int walletId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE wallet_id = ? ORDER BY timestamp DESC";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, walletId);
        ResultSet rs = stmt.executeQuery();

        System.out.println("\n📋 TRANSACTION HISTORY:");
        System.out.println("--------------------------------------------------");
        boolean hasRecords = false;

        while (rs.next()) {
            hasRecords = true;
            System.out.printf("   [%s] %-12s | %.2f TL%n",
                    rs.getString("timestamp"),
                    rs.getString("category"),
                    rs.getDouble("amount"));
        }

        if (!hasRecords) {
            System.out.println("   No transactions found.");
        }
        System.out.println("--------------------------------------------------");

        rs.close();
        stmt.close();
    }
}
