package service;

import model.Wallet;
import strategy.PaymentStrategy;

public class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Main method of the payment engine.
     * Processes the payment according to the selected strategy.
     */
    public boolean executePayment(double amount, Wallet wallet) {
        if (strategy == null) {
            System.err.println("Payment strategy not set!");
            return false;
        }
        System.out.println("Processing payment: " + amount + " TL");
        return strategy.processPayment(amount, wallet);
    }
}
