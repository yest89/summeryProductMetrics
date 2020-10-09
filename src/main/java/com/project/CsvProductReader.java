package com.project;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvProductReader {

    public List<Product> read(Path path) {
        // TODO: Add a check for valid file existing.

        List<Product> products = new ArrayList<>();
        try {
            int initialCapacity = (int) Files.lines(path).count();
            products = new ArrayList<>(initialCapacity);

            BufferedReader reader = Files.newBufferedReader(path);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                String country = record.get("origin_country");
                if (this.isCountryValid(country)) {
                    String ratingFiveCount = record.get("rating_five_count");
                    String ratingCount = record.get("rating_count");
                    String price = record.get("price");
                    Product product = new Product(
                            country,
                            this.convertToDouble(price),
                            this.convertToLong(ratingFiveCount),
                            this.convertToLong(ratingCount));
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private boolean isCountryValid(String country) {
        return country != null && !"".equals(country);
    }


    private long convertToLong(String value) {
        if (value == null || "".equals(value)) {
            return 0;
        }
        return Long.parseLong(value);
    }

    private double convertToDouble(String value) {
        if (value == null || "".equals(value)) {
            return 0;
        }
        return Double.parseDouble(value);
    }


    private static double calculateAveragePrice(List<Product> product) {
        return product.stream().collect(Collectors.averagingDouble(Product::getPrice));
    }

    private static long sumRatingFiveCount(List<Product> product) {
        return product.stream().collect(Collectors.summarizingLong(Product::getRatingFiveCount)).getSum();
    }

    private static long sumRatingCount(List<Product> product) {
        return product.stream().collect(Collectors.summarizingLong(Product::getRatingCount)).getSum();
    }

    public static void main(final String[] args) {
        CsvProductReader app = new CsvProductReader();

        //TODO Please set path to the file before launching app
        Path pathInput = Paths.get("test-task_dataset_summer_products.csv");
        List<Product> products = app.read(pathInput);

        Map<String, ProductMetrics> collect = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCountryProduct,
                        Collectors.collectingAndThen(Collectors.toList(),
                                product ->
                                        new ProductMetrics(
                                                calculateAveragePrice(product),
                                                sumRatingFiveCount(product),
                                                sumRatingCount(product)))));

        List<CountryProductMetrics> countryProductMetrics = collect.entrySet().stream()
                .map(met ->
                        new CountryProductMetrics(
                                met.getKey(),
                                met.getValue().getAveragePriceProducts(),
                                met.getValue().calculateFivePercentage()))
                .sorted(Comparator.comparing(CountryProductMetrics::getCountry))
                .collect(Collectors.toList());

        countryProductMetrics.forEach(metrics -> System.out.println("Product Metrics By Country: " + metrics));
    }
}