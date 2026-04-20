package model;

public class Wallet {
    private double creditCardLimit;
    private double foodBalance; // Corporate meal allowance
    private double chipPoints;     // Loyalty points earned from spending
    private boolean isMicroSavingEnabled; // Is round-up saving enabled?

    public Wallet(double creditCardLimit, double foodBalance, double chipPoints) {
        this.creditCardLimit = creditCardLimit;
        this.foodBalance = foodBalance;
        this.chipPoints = chipPoints;
        this.isMicroSavingEnabled = true; // Enabled by default
    }

    // Getter and Setter methods
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

    // These balances are updated as payments are processed

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
