package service;

import model.Wallet;
import strategy.PaymentStrategy;

/**
 * The payment service acts as the <b>Context</b> in the Strategy Pattern.
 * <p>
 * It delegates payment processing to a {@link PaymentStrategy} implementation,
 * allowing the payment algorithm to be selected and swapped at runtime based
 * on merchant category or user preference.
 *
 * @author Sarp Onaran
 * @version 1.0
 * @see PaymentStrategy
 */
public class PaymentService {
    private PaymentStrategy strategy;

    /**
     * Constructs a PaymentService with the given payment strategy.
     *
     * @param strategy the payment strategy to use for processing
     */
    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Changes the active payment strategy at runtime.
     *
     * @param strategy the new payment strategy to use
     */
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Executes a payment using the currently set strategy.
     *
     * @param amount the total amount to charge (in TL)
     * @param wallet the user's wallet containing funding sources
     * @return {@code true} if the payment completed successfully
     * @throws PaymentException if no strategy is set or the payment fails
     */
    public boolean executePayment(double amount, Wallet wallet) throws PaymentException {
        if (strategy == null) {
            throw new PaymentException("Payment strategy not set!", "NO_STRATEGY");
        }
        if (amount <= 0) {
            throw new PaymentException("Payment amount must be positive. Received: " + amount, "INVALID_AMOUNT");
        }

        System.out.println("Processing payment: " + amount + " TL");

        boolean result = strategy.processPayment(amount, wallet);
        if (!result) {
            throw new PaymentException(
                    "Payment of " + amount + " TL failed due to insufficient funds.",
                    "INSUFFICIENT_FUNDS"
            );
        }
        return true;
    }
}
