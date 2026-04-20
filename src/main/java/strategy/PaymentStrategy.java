package strategy;

import model.Wallet;

public interface PaymentStrategy {
    /**
     * @param amount Harcanacak toplam tutar
     * @param wallet Kullanıcının cüzdanı
     * @return İşlem başarılı mı?
     */
    boolean processPayment(double amount, Wallet wallet);
}
