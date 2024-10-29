package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.exceptions.InvalidDataException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.ProductDTO;
import com.backend.OnlineStore.model.mappers.ProductMapper;
import com.backend.OnlineStore.model.mappers.UserMapper;
import com.backend.OnlineStore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return productMapper.toDTO(product);
    }


    public ProductDTO saveProduct(ProductDTO productDTO) {

        if (productDTO.getTitle() == null || productDTO.getTitle().isEmpty()) {
            throw new InvalidDataException("Product title is required");
        }
        if (productDTO.getPrice() < 0) {
            throw new InvalidDataException("Product price must be positive");
        }



        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);

        return productMapper.toDTO(savedProduct);
    }


    public Optional<List<ProductDTO>> findProductsByCategory(Long categoryId) {
        return Optional.ofNullable(productRepository.findByCategoryId(categoryId)
                .map(products -> products.stream()
                        .map(productMapper::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + categoryId + " not found")));
    }


    public Optional<List<ProductDTO>> searchProductsByTitle(String title) {
        return Optional.ofNullable(productRepository.findByTitleContaining(title)
                .map(products -> products.stream()
                        .map(productMapper::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Product with title " + title + " not found")));
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
