package service;

import model.Wallet;

public class MicroSavingProcessor {

    public static void applyRoundUp(double originalAmount, Wallet wallet) {
        if (!wallet.isMicroSavingEnabled())
            return;

        // E.g.: Spending is 184.0 TL. Round up to the nearest ten.
        double roundedAmount = Math.ceil(originalAmount / 10.0) * 10.0;
        double roundUpDifference = roundedAmount - originalAmount;

        if (roundUpDifference > 0) {
            // Deduct the round-up difference from the card and convert to loyalty points
            if (wallet.getCreditCardLimit() >= roundUpDifference) {
                wallet.setCreditCardLimit(wallet.getCreditCardLimit() - roundUpDifference);
                wallet.setChipPoints(wallet.getChipPoints() + roundUpDifference);
                System.out.println("🐷 [Piggy Bank] Your " + originalAmount + " TL purchase was rounded up to "
                        + roundedAmount + " TL. "
                        + roundUpDifference + " TL added to your wallet as ChipPoints!");
            }
        }
    }
}