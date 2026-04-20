package service;

import model.Wallet;
import strategy.PaymentStrategy;
import strategy.RestaurantPaymentStrategy;
import strategy.RetailPaymentStrategy;

public class Main {
    public static void main(String[] args) {

        // 1. Simulation Start
        Wallet myWallet = new Wallet(5000.0, 400.0, 50.0);

        System.out.println("==================================================");
        System.out.println("🚀 FLEXPAY SMART WALLET ENGINE - V1.0");
        System.out.println("==================================================");
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
        }
        printWalletStatus(myWallet);

        System.out.println("\n✅ FLEXPAY SIMULATION COMPLETED SUCCESSFULLY.");
    }

    private static void printWalletStatus(Wallet wallet) {
        System.out.println("--------------------------------------------------");
        System.out.println("💳 [WALLET STATUS]");
        System.out.println("   Credit Card Limit  : " + wallet.getCreditCardLimit() + " TL");
        System.out.println("   Corporate Meal Fund : " + wallet.getFoodBalance() + " TL");
        System.out.println("   Loyalty Points      : " + wallet.getChipPoints() + " Points");
        System.out.println("--------------------------------------------------");
    }
}