package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products with pagination (user view)
    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.findAllProducts(pageable);
    }

    // Search products by title (user view)
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String title) {
        return productService.searchProductsByTitle(title);
    }

    // Get product by ID (user view)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    // Get products by category (user view)
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.findProductsByCategory(categoryId);
    }

    // Create or update a product (admin view)
    @PostMapping("/admin")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    // Delete a product by ID (admin view)
    @DeleteMapping("/admin/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
