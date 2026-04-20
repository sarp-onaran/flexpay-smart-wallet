package strategy;

import model.Wallet;

public class RetailPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean processPayment(double amount, Wallet wallet) {
        System.out.println("🛍️ Mağaza Ödemesi Başlatıldı: " + amount + " TL");
        double remainingAmount = amount;

        // Mağazada yemek bakiyesi KULLANILAMAZ. Doğrudan ChipPuan'a bakıyoruz.
        if (wallet.getChipPoints() > 0) {
            double amountToDeduct = Math.min(wallet.getChipPoints(), remainingAmount);
            wallet.setChipPoints(wallet.getChipPoints() - amountToDeduct);
            remainingAmount -= amountToDeduct;
            System.out.println("   -> ChipPuan'dan Çekilen: " + amountToDeduct + " TL");
        }

        if (remainingAmount > 0) {
            if (wallet.getCreditCardLimit() >= remainingAmount) {
                wallet.setCreditCardLimit(wallet.getCreditCardLimit() - remainingAmount);
                System.out.println("   -> Kredi Kartından Çekilen: " + remainingAmount + " TL");
                remainingAmount = 0;
            } else {
                System.out.println("❌ Yetersiz Bakiye!");
                return false;
            }
        }

        System.out.println("✅ Mağaza ödemesi başarıyla tamamlandı!\n");
        return true;
    }
}