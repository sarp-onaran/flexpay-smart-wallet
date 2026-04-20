package service;

import model.Wallet;
import strategy.PaymentStrategy;
import strategy.RestaurantPaymentStrategy;
import strategy.RetailPaymentStrategy;

public class Main {
    public static void main(String[] args) {

        // 1. Simülasyon Başlangıcı
        Wallet myWallet = new Wallet(5000.0, 400.0, 50.0);

        System.out.println("==================================================");
        System.out.println("🚀 FLEXPAY SMART WALLET ENGINE - V1.0");
        System.out.println("==================================================");
        printWalletStatus(myWallet);

        // ---------------------------------------------------------
        // SENARYO 1: Restoranda Otomatik Parçalı Ödeme
        // ---------------------------------------------------------
        System.out.println("\n📍 LOKASYON: Midpoint Restoran");
        double billAmount = 584.0;

        PaymentStrategy restaurantStrategy = new RestaurantPaymentStrategy();

        boolean isPaymentSuccessful = restaurantStrategy.processPayment(billAmount, myWallet);

        if (isPaymentSuccessful) {
            MicroSavingProcessor.applyRoundUp(billAmount, myWallet);
        }
        printWalletStatus(myWallet);

        // ---------------------------------------------------------
        // SENARYO 2: Giyim Mağazasında Ödeme
        // ---------------------------------------------------------
        System.out.println("\n📍 LOKASYON: LC Waikiki Mağazası");
        double retailAmount = 200.0;

        PaymentStrategy retailStrategy = new RetailPaymentStrategy();

        if (retailStrategy.processPayment(retailAmount, myWallet)) {
            MicroSavingProcessor.applyRoundUp(retailAmount, myWallet);
        }
        printWalletStatus(myWallet);

        System.out.println("\n✅ FLEXPAY SİMÜLASYONU BAŞARIYLA TAMAMLANDI.");
    }

    private static void printWalletStatus(Wallet wallet) {
        System.out.println("--------------------------------------------------");
        System.out.println("💳 [CÜZDAN DURUMU]");
        System.out.println("   Kredi Kartı Limiti : " + wallet.getCreditCardLimit() + " TL");
        System.out.println("   Kurumsal Yemek Fonu: " + wallet.getFoodBalance() + " TL");
        System.out.println("   Sadakat Puanı      : " + wallet.getChipPoints() + " Puan");
        System.out.println("--------------------------------------------------");
    }
}