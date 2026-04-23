package repository;

import java.sql.*;

public class TransactionRepository {
    private final Connection connection;

    public TransactionRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Logs a payment transaction to the database.
     *
     * @param walletId ID of the wallet that made the payment
     * @param category Payment category (e.g., "RESTAURANT", "RETAIL")
     * @param amount   Transaction amount
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
     * Prints all transactions for a given wallet.
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
