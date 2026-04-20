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
     * Ödeme motorunun ana metodu.
     * Seçilen stratejiye göre ödemeyi işler.
     */
    public boolean executePayment(double amount, Wallet wallet) {
        if (strategy == null) {
            System.err.println("Ödeme stratejisi belirlenmedi!");
            return false;
        }
        System.out.println("Ödeme işleniyor: " + amount + " TL");
        return strategy.processPayment(amount, wallet);
    }
}
