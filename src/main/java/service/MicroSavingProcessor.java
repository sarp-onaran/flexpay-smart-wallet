package service;

import model.Wallet;

/**
 * Implements the micro-saving (round-up) feature.
 * <p>
 * After each purchase, the spent amount is rounded up to the nearest ₺10,
 * and the difference is deducted from the credit card and converted into
 * loyalty points (ChipPoints). This encourages passive saving with every transaction.
 * <p>
 * Example: A ₺184 purchase is rounded to ₺190, and ₺6 is added as ChipPoints.
 *
 * @author Sarp Onaran
 * @version 1.0
 */
public class MicroSavingProcessor {

    /**
     * Applies the round-up micro-saving logic to a completed transaction.
     * <p>
     * If the wallet's micro-saving feature is disabled, this method does nothing.
     * If the credit card has insufficient limit for the round-up difference,
     * the round-up is silently skipped to avoid overdraft.
     *
     * @param originalAmount the original transaction amount in TL
     * @param wallet         the user's wallet to apply round-up savings to
     */
    public static void applyRoundUp(double originalAmount, Wallet wallet) {
        if (!wallet.isMicroSavingEnabled()) {
            return;
        }

        if (originalAmount <= 0) {
            return;
        }

        double roundedAmount = Math.ceil(originalAmount / 10.0) * 10.0;
        double roundUpDifference = roundedAmount - originalAmount;

        if (roundUpDifference > 0) {
            if (wallet.getCreditCardLimit() >= roundUpDifference) {
                wallet.setCreditCardLimit(wallet.getCreditCardLimit() - roundUpDifference);
                wallet.setChipPoints(wallet.getChipPoints() + roundUpDifference);
                System.out.println("🐷 [Piggy Bank] Your " + originalAmount + " TL purchase was rounded up to "
                        + roundedAmount + " TL. "
                        + roundUpDifference + " TL added to your wallet as ChipPoints!");
            } else {
                System.out.println("⚠️ [Piggy Bank] Skipped round-up: insufficient credit card limit.");
            }
        }
    }
}