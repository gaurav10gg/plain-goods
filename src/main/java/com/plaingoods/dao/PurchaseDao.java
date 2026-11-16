package com.plaingoods.dao;

import com.plaingoods.db.SqlDatabase;
import com.plaingoods.model.Purchase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDao {

    private static final String SELECT = """
            SELECT pu.id, u.email, p.name, pu.price, pu.purchased_at
            FROM purchases pu
            JOIN users u    ON u.id = pu.user_id
            JOIN products p ON p.id = pu.product_id
            """;

    public void buy(int userId, int productId, double price) {
        String sql = "INSERT INTO purchases (user_id, product_id, price) VALUES (?, ?, ?)";
        try (Connection c = SqlDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setDouble(3, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** One user's purchases (My orders page). */
    public List<Purchase> findByUser(int userId) {
        return query(SELECT + " WHERE pu.user_id = ? ORDER BY pu.id DESC", userId);
    }

    /** Everyone's purchases (admin page). */
    public List<Purchase> findAll() {
        return query(SELECT + " ORDER BY pu.id DESC", null);
    }

    private List<Purchase> query(String sql, Integer userId) {
        List<Purchase> purchases = new ArrayList<>();
        try (Connection c = SqlDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            if (userId != null) {
                ps.setInt(1, userId);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                purchases.add(new Purchase(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return purchases;
    }
}
