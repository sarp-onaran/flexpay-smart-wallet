package strategy;

import model.Wallet;

public class RetailPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean processPayment(double amount, Wallet wallet) {
        System.out.println("🛍️ Retail Payment Initiated: " + amount + " TL");
        double remainingAmount = amount;

        // Meal balance CANNOT be used at retail stores. Check ChipPoints directly.
        if (wallet.getChipPoints() > 0) {
            double amountToDeduct = Math.min(wallet.getChipPoints(), remainingAmount);
            wallet.setChipPoints(wallet.getChipPoints() - amountToDeduct);
            remainingAmount -= amountToDeduct;
            System.out.println("   -> Deducted from ChipPoints: " + amountToDeduct + " TL");
        }

        if (remainingAmount > 0) {
            if (wallet.getCreditCardLimit() >= remainingAmount) {
                wallet.setCreditCardLimit(wallet.getCreditCardLimit() - remainingAmount);
                System.out.println("   -> Charged to Credit Card: " + remainingAmount + " TL");
                remainingAmount = 0;
            } else {
                System.out.println("❌ Insufficient Balance!");
                return false;
            }
        }

        System.out.println("✅ Retail payment completed successfully!\n");
        return true;
    }
}