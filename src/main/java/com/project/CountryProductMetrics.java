package com.project;

public class CountryProductMetrics {
    private final String country;
    private final double averagePrice;
    private final long fivePercentage;

    public CountryProductMetrics(String country, double averagePrice, long fivePercentage) {
        this.country = country;
        this.averagePrice = averagePrice;
        this.fivePercentage = fivePercentage;
    }

    public String getCountry() {
        return country;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public long getFivePercentage() {
        return fivePercentage;
    }

    @Override
    public String toString() {
        return "CountryProductMetrics{" +
                "country='" + country + '\'' +
                ", averagePrice=" + averagePrice +
                ", fivePercentage=" + fivePercentage +
                '}';
    }
}
