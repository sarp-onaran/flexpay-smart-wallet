package repository;

import model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletRepository {
    private final Connection connection;

    public WalletRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a new wallet into the database and returns the generated ID.
     */
    public int insert(Wallet wallet) throws SQLException {
        String sql = "INSERT INTO wallets (owner_name, credit_card_limit, food_balance, chip_points, is_micro_saving_enabled) " +
                     "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, wallet.getOwnerName());
        stmt.setDouble(2, wallet.getCreditCardLimit());
        stmt.setDouble(3, wallet.getFoodBalance());
        stmt.setDouble(4, wallet.getChipPoints());
        stmt.setInt(5, wallet.isMicroSavingEnabled() ? 1 : 0);
        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        int generatedId = -1;
        if (keys.next()) {
            generatedId = keys.getInt(1);
            wallet.setId(generatedId);
        }

        keys.close();
        stmt.close();
        return generatedId;
    }

    /**
     * Finds a wallet by its ID. Returns null if not found.
     */
    public Wallet findById(int id) throws SQLException {
        String sql = "SELECT * FROM wallets WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        Wallet wallet = null;

        if (rs.next()) {
            wallet = mapResultSetToWallet(rs);
        }

        rs.close();
        stmt.close();
        return wallet;
    }

    /**
     * Returns all wallets stored in the database.
     */
    public List<Wallet> findAll() throws SQLException {
        String sql = "SELECT * FROM wallets";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Wallet> wallets = new ArrayList<>();
        while (rs.next()) {
            wallets.add(mapResultSetToWallet(rs));
        }

        rs.close();
        stmt.close();
        return wallets;
    }

    /**
     * Updates the wallet balances in the database after a transaction.
     */
    public void update(Wallet wallet) throws SQLException {
        String sql = "UPDATE wallets SET credit_card_limit = ?, food_balance = ?, chip_points = ?, " +
                     "is_micro_saving_enabled = ? WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, wallet.getCreditCardLimit());
        stmt.setDouble(2, wallet.getFoodBalance());
        stmt.setDouble(3, wallet.getChipPoints());
        stmt.setInt(4, wallet.isMicroSavingEnabled() ? 1 : 0);
        stmt.setInt(5, wallet.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Seeds the database with a default wallet if the table is empty.
     * Returns the existing or newly created wallet.
     */
    public Wallet seedDefaultWallet() throws SQLException {
        List<Wallet> existing = findAll();
        if (!existing.isEmpty()) {
            return existing.get(0);
        }

        Wallet defaultWallet = new Wallet("Sarp Onaran", 5000.0, 400.0, 50.0);
        insert(defaultWallet);
        System.out.println("📦 Default wallet seeded into database for: " + defaultWallet.getOwnerName());
        return defaultWallet;
    }

    /**
     * Maps a ResultSet row to a Wallet object.
     */
    private Wallet mapResultSetToWallet(ResultSet rs) throws SQLException {
        return new Wallet(
                rs.getInt("id"),
                rs.getString("owner_name"),
                rs.getDouble("credit_card_limit"),
                rs.getDouble("food_balance"),
                rs.getDouble("chip_points"),
                rs.getInt("is_micro_saving_enabled") == 1
        );
    }
}
