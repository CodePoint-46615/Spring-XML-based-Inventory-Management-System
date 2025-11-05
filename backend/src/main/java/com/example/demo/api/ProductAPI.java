package com.example.demo.api;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.service.ProductService;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1")
@Controller
@RequestMapping("/api")
public class ProductAPI {
    private ProductService productService;

    public ProductAPI(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    @ResponseBody
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PostMapping("/bulk")
    @ResponseBody
    public void addProducts(@RequestBody List<Product> products) {
        productService.addProducts(products);
    }

    @PutMapping("/products/{id}")
    @ResponseBody
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
    }

    @GetMapping("/category/{category}")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable ProductCategory category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/expiring")
    @ResponseBody
    public List<Product> getExpiringProducts() {
        return productService.getExpiringProducts(7);
    }

    @GetMapping("/discounted")
    @ResponseBody
    public List<Product> getDiscountedProducts() {
        return productService.getDiscountedProducts(0.20);
    }

    @GetMapping("/report/category-value")
    @ResponseBody
    public Map<ProductCategory, Double> getCategoryWiseTotalValue() {
        return productService.getCategoryWiseTotalValue();
    }
}
