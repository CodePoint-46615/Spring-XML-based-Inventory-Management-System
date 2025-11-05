package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.repository.ProductRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void addProducts(List<Product> products) {
        productRepository.addProducts(products);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public List<Product> getProductsByCategory(ProductCategory category) {
        return productRepository.getProductsByCategory(category);
    }

    public List<Product> getExpiringProducts(int days) {
        return productRepository.getExpiringProducts(days);
    }

    public List<Product> getDiscountedProducts(double discountRate) {
        List<Product> expiringProducts = getExpiringProducts(7);
        for (Product product : expiringProducts) {
            product.setDiscount(discountRate);
        }
        return expiringProducts;
    }

    public Map<ProductCategory, Double> getCategoryWiseTotalValue() {
        return productRepository.getCategoryWiseTotalValue();
    }
}