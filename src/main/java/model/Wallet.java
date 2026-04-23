package model;

/**
 * Represents a user's digital wallet containing multiple funding sources.
 * <p>
 * A wallet manages three types of balances:
 * <ul>
 *     <li><b>Credit Card Limit</b> — the remaining spendable limit on the user's card</li>
 *     <li><b>Food Balance</b> — corporate meal allowance (restricted to food-related merchants)</li>
 *     <li><b>ChipPoints</b> — loyalty points earned through spending and micro-saving</li>
 * </ul>
 * The wallet also supports a micro-saving feature that rounds up purchases and
 * converts the difference into loyalty points.
 *
 * @author Sarp Onaran
 * @version 1.1
 */
public class Wallet {
    private int id;
    private String ownerName;
    private double creditCardLimit;
    private double foodBalance;
    private double chipPoints;
    private boolean isMicroSavingEnabled;

    /**
     * Constructs a new Wallet without a database ID (for initial creation).
     * Micro-saving is enabled by default.
     *
     * @param ownerName       the name of the wallet owner
     * @param creditCardLimit initial credit card spending limit in TL
     * @param foodBalance     initial corporate meal allowance in TL
     * @param chipPoints      initial loyalty points balance
     */
    public Wallet(String ownerName, double creditCardLimit, double foodBalance, double chipPoints) {
        this.ownerName = ownerName;
        this.creditCardLimit = creditCardLimit;
        this.foodBalance = foodBalance;
        this.chipPoints = chipPoints;
        this.isMicroSavingEnabled = true;
    }

    /**
     * Constructs a Wallet loaded from the database with all persisted fields.
     *
     * @param id                    database primary key
     * @param ownerName             the name of the wallet owner
     * @param creditCardLimit       current credit card spending limit in TL
     * @param foodBalance           current corporate meal allowance in TL
     * @param chipPoints            current loyalty points balance
     * @param isMicroSavingEnabled  whether the round-up saving feature is active
     */
    public Wallet(int id, String ownerName, double creditCardLimit, double foodBalance,
                  double chipPoints, boolean isMicroSavingEnabled) {
        this.id = id;
        this.ownerName = ownerName;
        this.creditCardLimit = creditCardLimit;
        this.foodBalance = foodBalance;
        this.chipPoints = chipPoints;
        this.isMicroSavingEnabled = isMicroSavingEnabled;
    }

    /**
     * Returns the database ID of this wallet.
     *
     * @return the wallet ID, or 0 if not yet persisted
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the database ID of this wallet.
     *
     * @param id the wallet ID assigned by the database
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the wallet owner.
     *
     * @return the owner's full name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the name of the wallet owner.
     *
     * @param ownerName the owner's full name
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Returns the remaining credit card spending limit.
     *
     * @return credit card limit in TL
     */
    public double getCreditCardLimit() {
        return creditCardLimit;
    }

    /**
     * Updates the credit card spending limit.
     *
     * @param creditCardLimit new credit card limit in TL
     */
    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    /**
     * Returns the remaining corporate meal allowance.
     *
     * @return food balance in TL
     */
    public double getFoodBalance() {
        return foodBalance;
    }

    /**
     * Updates the corporate meal allowance.
     *
     * @param foodBalance new food balance in TL
     */
    public void setFoodBalance(double foodBalance) {
        this.foodBalance = foodBalance;
    }

    /**
     * Returns the current loyalty points balance.
     *
     * @return chip points balance
     */
    public double getChipPoints() {
        return chipPoints;
    }

    /**
     * Updates the loyalty points balance.
     *
     * @param chipPoints new chip points balance
     */
    public void setChipPoints(double chipPoints) {
        this.chipPoints = chipPoints;
    }

    /**
     * Checks whether the micro-saving (round-up) feature is enabled.
     *
     * @return {@code true} if micro-saving is active, {@code false} otherwise
     */
    public boolean isMicroSavingEnabled() {
        return isMicroSavingEnabled;
    }

    /**
     * Enables or disables the micro-saving (round-up) feature.
     *
     * @param microSavingEnabled {@code true} to enable, {@code false} to disable
     */
    public void setMicroSavingEnabled(boolean microSavingEnabled) {
        isMicroSavingEnabled = microSavingEnabled;
    }

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
