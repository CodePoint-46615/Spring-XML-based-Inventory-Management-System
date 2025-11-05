package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private static final List<Product> productList = new ArrayList<>();
    private static int nextId = 1;

    static {
        LocalDate today = LocalDate.now();

        // Beauty Care products
        addStaticProduct(new Product(1, "Shampoo", ProductCategory.BEAUTY_CARE, 400.00, 50, today.plusDays(30)));
        addStaticProduct(new Product(2, "Conditioner", ProductCategory.BEAUTY_CARE, 150.00, 40, today.plusDays(60)));
        addStaticProduct(new Product(3, "Face Cream", ProductCategory.BEAUTY_CARE, 350.00, 30, today.plusDays(5))); // Expiring soon

        // Vegetables
        addStaticProduct(new Product(4, "Carrots", ProductCategory.VEGETABLES, 45.00, 100, today.plusDays(3))); // Expiring soon
        addStaticProduct(new Product(5, "Broccoli", ProductCategory.VEGETABLES, 60.00, 80, today.plusDays(7)));
        addStaticProduct(new Product(6, "Potatoes", ProductCategory.VEGETABLES, 25.00, 150, today.plusDays(30)));

        // Meat
        addStaticProduct(new Product(7, "Chicken Breast", ProductCategory.MEAT, 200.00, 60, today.plusDays(2))); // Expiring soon
        addStaticProduct(new Product(8, "Mutton", ProductCategory.MEAT, 1000.00, 45, today.plusDays(4))); // Expiring soon
        addStaticProduct(new Product(9, "Salmon Fillet", ProductCategory.MEAT, 950.00, 30, today.plusDays(10)));

        // Groceries
        addStaticProduct(new Product(10, "Rice 5kg", ProductCategory.GROCERIES, 430.00, 40, today.plusDays(365)));
        addStaticProduct(new Product(11, "Pasta", ProductCategory.GROCERIES, 120.00, 75, today.plusDays(180)));
        addStaticProduct(new Product(12, "Canned Beans", ProductCategory.GROCERIES, 100.00, 120, today.plusDays(730)));

        // Others
        addStaticProduct(new Product(13, "Batteries AA", ProductCategory.OTHERS, 25.00, 200, null)); // No expiry
        addStaticProduct(new Product(14, "Light Bulbs", ProductCategory.OTHERS, 220.00, 90, null)); // No expiry

        // Expired
        addStaticProduct(new Product(15, "Expired Yogurt", ProductCategory.GROCERIES, 350.00, 0, today.minusDays(1)));
        addStaticProduct(new Product(16, "Old Cheese", ProductCategory.GROCERIES, 250.00, 0, today.minusDays(10)));
    }

    private static void addStaticProduct(Product product) {
        product.setId(nextId++);
        product.checkAvailability();
        productList.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }

    public Product getProductById(int id) {
        return productList
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        product.setId(nextId++);
        product.checkAvailability();
        productList.add(product);
    }

    public void addProducts(List<Product> products) {
        for (Product product : products) {
            addProduct(product);
        }
    }

    public boolean updateProduct(Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            Product existing = productList.get(i);
            if (existing.getId() == updatedProduct.getId()) {
                updatedProduct.checkAvailability();
                productList.set(i, updatedProduct);
                return true;
            }
        }
        return false;
    }

    public List<Product> getProductsByCategory(ProductCategory category) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory() == category) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> getExpiringProducts(int days) {
        List<Product> result = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate threshold = today.plusDays(days);

        for (Product product : productList) {
            if (product.getExpiryDate() != null &&
                    !product.getExpiryDate().isBefore(today) &&
                    !product.getExpiryDate().isAfter(threshold)) {
                result.add(product);
            }
        }
        return result;
    }

    public Map<ProductCategory, Double> getCategoryWiseTotalValue() {
        Map<ProductCategory, Double> result = new HashMap<>();
        for (Product product : productList) {
            if (product.isAvailable()) {
                double value = product.getDiscountedPrice() * product.getQuantity();
                result.merge(product.getCategory(), value, Double::sum);
            }
        }
        return result;
    }
}
