package strategy;

import model.Wallet;

public class RestaurantPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean processPayment(double amount, Wallet wallet) {
        System.out.println("🍽️ Restaurant Payment Initiated: " + amount + " TL");
        double remainingAmount = amount;

        // Step 1: Use the corporate meal balance first
        if (wallet.getFoodBalance() > 0) {
            double amountToDeduct = Math.min(wallet.getFoodBalance(), remainingAmount);
            wallet.setFoodBalance(wallet.getFoodBalance() - amountToDeduct);
            remainingAmount -= amountToDeduct;
            System.out.println("   -> Deducted from Meal Balance: " + amountToDeduct + " TL");
        }

        // Step 2: If the bill is not settled, use ChipPoints
        if (remainingAmount > 0 && wallet.getChipPoints() > 0) {
            double amountToDeduct = Math.min(wallet.getChipPoints(), remainingAmount);
            wallet.setChipPoints(wallet.getChipPoints() - amountToDeduct);
            remainingAmount -= amountToDeduct;
            System.out.println("   -> Deducted from ChipPoints: " + amountToDeduct + " TL");
        }

        // Step 3: If there is still a remaining balance, charge the credit card
        if (remainingAmount > 0) {
            if (wallet.getCreditCardLimit() >= remainingAmount) {
                wallet.setCreditCardLimit(wallet.getCreditCardLimit() - remainingAmount);
                System.out.println("   -> Charged to Credit Card: " + remainingAmount + " TL");
                remainingAmount = 0;
            } else {
                System.out.println("❌ Insufficient Balance! Credit card limit cannot cover the transaction.");
                return false;
            }
        }

        System.out.println("✅ Restaurant payment completed successfully!\n");
        return true;
    }
}