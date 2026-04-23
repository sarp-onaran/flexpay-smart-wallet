package service;

import model.Wallet;
import repository.DatabaseManager;
import repository.WalletRepository;
import repository.TransactionRepository;
import strategy.PaymentStrategy;
import strategy.RestaurantPaymentStrategy;
import strategy.RetailPaymentStrategy;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("🚀 FLEXPAY SMART WALLET ENGINE - V1.0");
        System.out.println("==================================================");

        // 1. Initialize database and repositories
        DatabaseManager db = DatabaseManager.getInstance();
        WalletRepository walletRepo = new WalletRepository(db.getConnection());
        TransactionRepository txRepo = new TransactionRepository(db.getConnection());

        try {
            // 2. Load wallet from database (seed if first run)
            Wallet myWallet = walletRepo.seedDefaultWallet();
            System.out.println("👤 Wallet loaded for: " + myWallet.getOwnerName());
            printWalletStatus(myWallet);

            // ---------------------------------------------------------
            // SCENARIO 1: Automatic Split Payment at a Restaurant
            // ---------------------------------------------------------
            System.out.println("\n📍 LOCATION: Midpoint Restaurant");
            double billAmount = 584.0;

            PaymentStrategy restaurantStrategy = new RestaurantPaymentStrategy();
            boolean isPaymentSuccessful = restaurantStrategy.processPayment(billAmount, myWallet);

            if (isPaymentSuccessful) {
                MicroSavingProcessor.applyRoundUp(billAmount, myWallet);
                txRepo.logTransaction(myWallet.getId(), "RESTAURANT", billAmount);
            }
            printWalletStatus(myWallet);

            // ---------------------------------------------------------
            // SCENARIO 2: Payment at a Retail Store
            // ---------------------------------------------------------
            System.out.println("\n📍 LOCATION: LC Waikiki Store");
            double retailAmount = 200.0;

            PaymentStrategy retailStrategy = new RetailPaymentStrategy();

            if (retailStrategy.processPayment(retailAmount, myWallet)) {
                MicroSavingProcessor.applyRoundUp(retailAmount, myWallet);
                txRepo.logTransaction(myWallet.getId(), "RETAIL", retailAmount);
            }
            printWalletStatus(myWallet);

            // 3. Save updated wallet balances back to the database
            walletRepo.update(myWallet);
            System.out.println("\n💾 Wallet balances saved to database.");

            // 4. Print transaction history
            txRepo.printTransactionHistory(myWallet.getId());

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            db.closeConnection();
        }

        System.out.println("\n✅ FLEXPAY SIMULATION COMPLETED SUCCESSFULLY.");
    }

    private static void printWalletStatus(Wallet wallet) {
        System.out.println("--------------------------------------------------");
        System.out.println("💳 [WALLET STATUS] Owner: " + wallet.getOwnerName());
        System.out.println("   Credit Card Limit  : " + wallet.getCreditCardLimit() + " TL");
        System.out.println("   Corporate Meal Fund : " + wallet.getFoodBalance() + " TL");
        System.out.println("   Loyalty Points      : " + wallet.getChipPoints() + " Points");
        System.out.println("--------------------------------------------------");
    }
}