package model;

public class Wallet {
    private double creditCardLimit;
    private double foodBalance; // Şirketin yatırdığı yemek parası
    private double chipPoints;     // Harcadıkça kazanılan puanlar
    private boolean isMicroSavingEnabled; // Küsurat yuvarlama açık mı?

    public Wallet(double creditCardLimit, double foodBalance, double chipPoints) {
        this.creditCardLimit = creditCardLimit;
        this.foodBalance = foodBalance;
        this.chipPoints = chipPoints;
        this.isMicroSavingEnabled = true; // Varsayılan olarak açık
    }

    // Getter ve Setter metotları
    public double getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public double getFoodBalance() {
        return foodBalance;
    }

    public void setFoodBalance(double foodBalance) {
        this.foodBalance = foodBalance;
    }

    public double getChipPoints() {
        return chipPoints;
    }

    public void setChipPoints(double chipPoints) {
        this.chipPoints = chipPoints;
    }

    public boolean isMicroSavingEnabled() {
        return isMicroSavingEnabled;
    }

    public void setMicroSavingEnabled(boolean microSavingEnabled) {
        isMicroSavingEnabled = microSavingEnabled;
    }

    // Ödeme yapıldıkça bu bakiyeler güncellenecek

    @Override
    public String toString() {
        return "Wallet{" +
                "creditCardLimit=" + creditCardLimit +
                ", foodBalance=" + foodBalance +
                ", chipPoints=" + chipPoints +
                ", isMicroSavingEnabled=" + isMicroSavingEnabled +
                '}';
    }
}
