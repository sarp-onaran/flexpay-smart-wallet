import model.Wallet;
import repository.DatabaseManager;
import repository.WalletRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Chippin Smart Wallet Engine ===");
        System.out.println("Project initialized successfully!\n");

        // Initialize database connection
        DatabaseManager db = DatabaseManager.getInstance();
        WalletRepository walletRepo = new WalletRepository(db.getConnection());

        try {
            // Load or create default wallet from database
            Wallet wallet = walletRepo.seedDefaultWallet();
            System.out.println("Wallet loaded: " + wallet);
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        // Close the connection
        db.closeConnection();
    }
}
