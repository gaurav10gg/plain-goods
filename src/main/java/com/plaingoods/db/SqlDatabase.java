package com.plaingoods.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/** SQL database (SQLite file: data/shop.db) — users, products, purchases. */
public class SqlDatabase {

    private static String url;

    public static void init(String filePath) throws Exception {
        Class.forName("org.sqlite.JDBC");
        File file = new File(filePath).getAbsoluteFile();
        file.getParentFile().mkdirs();
        url = "jdbc:sqlite:" + file.getPath();

        try (Connection c = connect(); Statement st = c.createStatement()) {
            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS users (
                      id       INTEGER PRIMARY KEY AUTOINCREMENT,
                      name     TEXT NOT NULL,
                      email    TEXT NOT NULL UNIQUE,
                      password TEXT NOT NULL
                    )""");
            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS products (
                      id          INTEGER PRIMARY KEY AUTOINCREMENT,
                      name        TEXT NOT NULL,
                      description TEXT,
                      price       REAL NOT NULL
                    )""");
            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS purchases (
                      id           INTEGER PRIMARY KEY AUTOINCREMENT,
                      user_id      INTEGER NOT NULL REFERENCES users(id),
                      product_id   INTEGER NOT NULL REFERENCES products(id),
                      price        REAL NOT NULL,
                      purchased_at TEXT DEFAULT (datetime('now', 'localtime'))
                    )""");
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
