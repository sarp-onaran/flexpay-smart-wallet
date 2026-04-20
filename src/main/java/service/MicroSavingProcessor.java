package service;

import model.Wallet;

public class MicroSavingProcessor {

    public static void applyRoundUp(double originalAmount, Wallet wallet) {
        if (!wallet.isMicroSavingEnabled())
            return;

        // Örn: Harcama 184.0 TL. Bir üst 10'luğa veya 100'lüğe yuvarlayalım (Burada en
        // yakın onluğa yuvarlıyoruz)
        double roundedAmount = Math.ceil(originalAmount / 10.0) * 10.0;
        double roundUpDifference = roundedAmount - originalAmount;

        if (roundUpDifference > 0) {
            // Kullanıcının kartından küsuratı çek ve puana çevir
            if (wallet.getCreditCardLimit() >= roundUpDifference) {
                wallet.setCreditCardLimit(wallet.getCreditCardLimit() - roundUpDifference);
                wallet.setChipPoints(wallet.getChipPoints() + roundUpDifference);
                System.out.println("🐷 [Kumbaraya Atıldı] " + originalAmount + " TL harcamanız "
                        + roundedAmount + " TL'ye yuvarlandı. "
                        + roundUpDifference + " TL ChipPuan olarak cüzdanınıza eklendi!");
            }
        }
    }
}