package strategy;

import model.Wallet;

/**
 * Defines the contract for payment processing strategies.
 * <p>
 * Each implementation encodes the business rules for a specific merchant category
 * (e.g., restaurants, retail stores). The Strategy Pattern allows the payment engine
 * to switch between different payment algorithms at runtime.
 *
 * @author Sarp Onaran
 * @version 1.0
 * @see RestaurantPaymentStrategy
 * @see RetailPaymentStrategy
 */
public interface PaymentStrategy {

    /**
     * Processes a payment by distributing the amount across available funding
     * sources in the wallet according to the strategy's business rules.
     *
     * @param amount the total amount to be charged (in TL, must be positive)
     * @param wallet the user's wallet containing all funding sources
     * @return {@code true} if the payment was completed successfully,
     *         {@code false} if there were insufficient funds
     * @throws IllegalArgumentException if amount is negative or zero
     */
    boolean processPayment(double amount, Wallet wallet);
}
