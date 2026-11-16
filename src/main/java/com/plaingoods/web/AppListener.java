package com.plaingoods.web;

import com.plaingoods.dao.AdminDao;
import com.plaingoods.dao.ProductDao;
import com.plaingoods.db.NoSqlDatabase;
import com.plaingoods.db.SqlDatabase;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/** Runs once on startup: connects both databases and adds some starter data. */
@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            SqlDatabase.init(System.getProperty("shop.db", "data/shop.db"));
        } catch (Exception e) {
            throw new RuntimeException("Could not open the SQLite database", e);
        }

        ProductDao products = new ProductDao();
        if (products.isEmpty()) {
            products.add("Ceramic Mug", "350 ml stoneware with a matte glaze.", 449);
            products.add("Dot Grid Notebook", "A5, 160 pages. Lays flat when open.", 399);
            products.add("Canvas Tote", "Heavy cotton canvas with an inside pocket.", 599);
            products.add("Desk Lamp", "Aluminium arm, warm dimmable LED.", 2499);
            products.add("Steel Bottle", "750 ml, keeps drinks cold for 24 hours.", 899);
            products.add("Wireless Mouse", "Silent clicks, USB-C rechargeable.", 1199);
        }

        NoSqlDatabase.init();
        try {
            new AdminDao().createDefaultAdmin();
        } catch (Exception e) {
            System.out.println("MongoDB is not running. Start it with start-mongo.cmd (admin login needs it).");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        NoSqlDatabase.close();
    }
}
