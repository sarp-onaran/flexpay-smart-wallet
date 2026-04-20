package strategy;

import model.Wallet;

public interface PaymentStrategy {
    /**
     * @param amount Total amount to be charged
     * @param wallet The user's wallet
     * @return Whether the transaction was successful
     */
    boolean processPayment(double amount, Wallet wallet);
}
