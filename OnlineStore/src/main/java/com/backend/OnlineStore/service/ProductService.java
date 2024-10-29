package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.entity.Category;
import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.entity.ProductType;
import com.backend.OnlineStore.exceptions.InvalidDataException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.ProductDTO;
import com.backend.OnlineStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }


    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        Long categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
        Long authorId = product.getAuthor() != null ? product.getAuthor().getId() : null;

        return new ProductDTO(
                product.getTitle(),
                product.getDescription(),
                (double) product.getAvailability(),
                product.getPrice(),
                product.getProductType().name(),
                categoryId,
                authorId
        );
    }

    public Product toEntity(ProductDTO productDTO, Category category, Author author) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setAvailability((int) productDTO.getAvailability());
        product.setPrice(productDTO.getPrice());
        product.setProductType(ProductType.valueOf(productDTO.getProductType()));

        if (category != null) {
            product.setCategory(category);
        }
        if (author != null) {
            product.setAuthor(author);
        }

        return product;
    }

    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return toDTO(product);
    }


    public ProductDTO saveProduct(ProductDTO productDTO, Category category, Author author) {


        if (productDTO.getTitle() == null || productDTO.getTitle().isEmpty()) {
            throw new InvalidDataException("Product title is required");
        }
        if (productDTO.getPrice() < 0) {
            throw new InvalidDataException("Product price must be positive");
        }

        Product product = toEntity(productDTO, category, author);

        Product savedProduct = productRepository.save(product);


        return toDTO(savedProduct);
    }

    public Optional<List<ProductDTO>> findProductsByCategory(Long categoryId) {
        return Optional.ofNullable(productRepository.findByCategoryId(categoryId)
                .map(products -> products.stream()
                        .map(this::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + categoryId + " not found")));
    }


    public Optional<List<ProductDTO>> searchProductsByTitle(String title) {
        return Optional.ofNullable(productRepository.findByTitleContaining(title)
                .map(products -> products.stream()
                        .map(this::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Product with title " + title + " not found")));
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
