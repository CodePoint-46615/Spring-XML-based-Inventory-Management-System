package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class Product {
    private int id;
    private String name;
    private ProductCategory category;
    private double price;
    private int quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
    private Double discount;
    private boolean available;

    // Constructors
    public Product() {}

    public Product(int id, String name, ProductCategory category, double price,
                   int quantity, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.available = true;
        this.discount = null;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ProductCategory getCategory() { return category; }
    public void setCategory(ProductCategory category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    // Business logic methods
    public double getDiscountedPrice() {
        if (discount != null && discount > 0) {
            return price * (1 - discount);
        }
        return price;
    }

    public void checkAvailability() {
        if (expiryDate != null && expiryDate.isBefore(LocalDate.now())) {
            this.available = false;
        }
    }
}