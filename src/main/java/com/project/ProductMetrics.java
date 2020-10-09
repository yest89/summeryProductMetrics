package com.project;

public class ProductMetrics {
    private final double averagePriceProducts;
    private final long sumRatingFiveCount;
    private final long sumRatingCount;

    public ProductMetrics(double averagePriceProducts, long sumRatingFiveCount, long sumRatingCount) {
        this.averagePriceProducts = averagePriceProducts;
        this.sumRatingFiveCount = sumRatingFiveCount;
        this.sumRatingCount = sumRatingCount;
    }

    public double getAveragePriceProducts() {
        return averagePriceProducts;
    }

    public long getSumRatingFiveCount() {
        return sumRatingFiveCount;
    }

    public long getSumRatingCount() {
        return sumRatingCount;
    }

    public long calculateFivePercentage() {
        if (sumRatingCount == 0) {
            return 0;
        }
        return (sumRatingFiveCount / sumRatingCount) * 100;
    }
}
