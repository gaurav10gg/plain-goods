package com.plaingoods.dao;

import com.plaingoods.db.SqlDatabase;
import com.plaingoods.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, description, price FROM products ORDER BY id";
        try (Connection c = SqlDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(toProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product findById(int id) {
        String sql = "SELECT id, name, description, price FROM products WHERE id = ?";
        try (Connection c = SqlDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? toProduct(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String name, String description, double price) {
        String sql = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";
        try (Connection c = SqlDatabase.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isEmpty() {
        return findAll().isEmpty();
    }

    private Product toProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"));
    }
}
