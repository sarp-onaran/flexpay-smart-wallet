package strategy;

import model.Wallet;

/**
 * Payment strategy for retail stores and general merchandise.
 * <p>
 * Unlike restaurant payments, this strategy does <b>not</b> use
 * the corporate meal balance. Payment sources are applied in this order:
 * <ol>
 *     <li><b>ChipPoints</b> — loyalty points are used first</li>
 *     <li><b>Credit Card</b> — remaining amount is charged to the card</li>
 * </ol>
 *
 * @author Sarp Onaran
 * @version 1.0
 */
public class RetailPaymentStrategy implements PaymentStrategy {

    /**
     * Processes a retail payment using the points → card priority.
     * Meal balance is explicitly excluded from retail transactions.
     *
     * @param amount the total purchase amount in TL
     * @param wallet the user's wallet
     * @return {@code true} if payment succeeded, {@code false} if insufficient funds
     * @throws IllegalArgumentException if amount is not positive
     */
    @Override
    public boolean processPayment(double amount, Wallet wallet) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive. Received: " + amount);
        }

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
            } else {
                System.out.println("❌ Insufficient Balance!");
                return false;
            }
        }

        System.out.println("✅ Retail payment completed successfully!\n");
        return true;
    }
}