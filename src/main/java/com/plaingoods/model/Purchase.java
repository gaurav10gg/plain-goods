package com.plaingoods.model;

public class Purchase {

    private final int id;
    private final String userEmail;
    private final String productName;
    private final double price;
    private final String purchasedAt;

    public Purchase(int id, String userEmail, String productName, double price, String purchasedAt) {
        this.id = id;
        this.userEmail = userEmail;
        this.productName = productName;
        this.price = price;
        this.purchasedAt = purchasedAt;
    }

    public int getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getPurchasedAt() {
        return purchasedAt;
    }

    public String getPriceText() {
        return String.format("₹%,.2f", price);
    }
}
