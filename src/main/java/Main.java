import model.Wallet;
import service.PaymentService;
import repository.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Chippin Smart Wallet Engine ===");
        System.out.println("Project initialized successfully!\n");

        // Create a sample wallet
        Wallet wallet = new Wallet(5000.0, 1500.0, 200.0);
        System.out.println("Wallet details: " + wallet);

        // Initialize database connection
        DatabaseManager db = DatabaseManager.getInstance();

        // TODO: Strategy implementations will be added
        // PaymentService service = new PaymentService(new MarketStrategy());
        // service.executePayment(150.0, wallet);

        // Close the connection
        db.closeConnection();
    }
}
