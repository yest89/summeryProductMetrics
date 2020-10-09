package com.project;

public class Product {
    private String countryProduct;
    private double price;
    private long ratingCount;
    private long ratingFiveCount;

    public Product(String countryProduct, double price, long ratingCount, long ratingFiveCount) {
        this.countryProduct = countryProduct;
        this.price = price;
        this.ratingCount = ratingCount;
        this.ratingFiveCount = ratingFiveCount;
    }

    public String getCountryProduct() {
        return countryProduct;
    }

    public double getPrice() {
        return price;
    }

    public long getRatingCount() {
        return ratingCount;
    }

    public long getRatingFiveCount() {
        return ratingFiveCount;
    }
}
