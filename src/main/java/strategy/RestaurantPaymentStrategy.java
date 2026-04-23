package strategy;

import model.Wallet;

/**
 * Payment strategy for restaurant and food-related merchants.
 * <p>
 * This strategy distributes the payment across three funding sources
 * in the following priority order:
 * <ol>
 *     <li><b>Meal Balance</b> — corporate meal allowance is used first</li>
 *     <li><b>ChipPoints</b> — loyalty points cover the remaining amount</li>
 *     <li><b>Credit Card</b> — any leftover is charged to the card</li>
 * </ol>
 *
 * @author Sarp Onaran
 * @version 1.0
 */
public class RestaurantPaymentStrategy implements PaymentStrategy {

    /**
     * Processes a restaurant payment using the meal → points → card priority.
     *
     * @param amount the total bill amount in TL
     * @param wallet the user's wallet
     * @return {@code true} if payment succeeded, {@code false} if insufficient funds
     * @throws IllegalArgumentException if amount is not positive
     */
    @Override
    public boolean processPayment(double amount, Wallet wallet) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive. Received: " + amount);
        }

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
            } else {
                System.out.println("❌ Insufficient Balance! Credit card limit cannot cover the transaction.");
                return false;
            }
        }

        System.out.println("✅ Restaurant payment completed successfully!\n");
        return true;
    }
}