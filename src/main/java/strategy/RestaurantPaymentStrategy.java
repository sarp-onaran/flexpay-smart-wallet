package strategy;

import model.Wallet;

public class RestaurantPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean processPayment(double amount, Wallet wallet) {
        System.out.println("🍽️ Restoran Ödemesi Başlatıldı: " + amount + " TL");
        double remainingAmount = amount;

        // 1. Adım: Önce şirketin verdiği yemek bakiyesini kullan
        if (wallet.getFoodBalance() > 0) {
            double amountToDeduct = Math.min(wallet.getFoodBalance(), remainingAmount);
            wallet.setFoodBalance(wallet.getFoodBalance() - amountToDeduct);
            remainingAmount -= amountToDeduct;
            System.out.println("   -> Yemek Bakiyesinden Çekilen: " + amountToDeduct + " TL");
        }

        // 2. Adım: Hesap kapanmadıysa ChipPuanları kullan
        if (remainingAmount > 0 && wallet.getChipPoints() > 0) {
            double amountToDeduct = Math.min(wallet.getChipPoints(), remainingAmount);
            wallet.setChipPoints(wallet.getChipPoints() - amountToDeduct);
            remainingAmount -= amountToDeduct;
            System.out.println("   -> ChipPuan'dan Çekilen: " + amountToDeduct + " TL");
        }

        // 3. Adım: Hala borç varsa Kredi Kartından çek
        if (remainingAmount > 0) {
            if (wallet.getCreditCardLimit() >= remainingAmount) {
                wallet.setCreditCardLimit(wallet.getCreditCardLimit() - remainingAmount);
                System.out.println("   -> Kredi Kartından Çekilen: " + remainingAmount + " TL");
                remainingAmount = 0;
            } else {
                System.out.println("❌ Yetersiz Bakiye! Kredi kartı limiti işlemi karşılamıyor.");
                return false;
            }
        }

        System.out.println("✅ Restoran ödemesi başarıyla tamamlandı!\n");
        return true;
    }
}