package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.exceptions.InvalidDataException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    public Product saveProduct(Product product) {
        if (product.getTitle() == null || product.getTitle().isEmpty()) {
            throw new InvalidDataException("Product title is required");
        }
        if (product.getPrice() < 0) {
            throw new InvalidDataException("Product price must be positive");
        }
        return productRepository.save(product);
    }


    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    public List<Product> findProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


    public List<Product> searchProductsByTitle(String title) {
        return productRepository.findByTitleContaining(title);
    }




    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
