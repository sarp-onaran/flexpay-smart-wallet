package model;

public class Wallet {
    private int id;
    private String ownerName;
    private double creditCardLimit;
    private double foodBalance; // Corporate meal allowance
    private double chipPoints;     // Loyalty points earned from spending
    private boolean isMicroSavingEnabled; // Is round-up saving enabled?

    // Constructor for creating a new wallet (without ID, before DB insert)
    public Wallet(String ownerName, double creditCardLimit, double foodBalance, double chipPoints) {
        this.ownerName = ownerName;
        this.creditCardLimit = creditCardLimit;
        this.foodBalance = foodBalance;
        this.chipPoints = chipPoints;
        this.isMicroSavingEnabled = true; // Enabled by default
    }

    // Constructor for loading from database (with ID)
    public Wallet(int id, String ownerName, double creditCardLimit, double foodBalance,
                  double chipPoints, boolean isMicroSavingEnabled) {
        this.id = id;
        this.ownerName = ownerName;
        this.creditCardLimit = creditCardLimit;
        this.foodBalance = foodBalance;
        this.chipPoints = chipPoints;
        this.isMicroSavingEnabled = isMicroSavingEnabled;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

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
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", creditCardLimit=" + creditCardLimit +
                ", foodBalance=" + foodBalance +
                ", chipPoints=" + chipPoints +
                ", isMicroSavingEnabled=" + isMicroSavingEnabled +
                '}';
    }
}
