package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.exceptions.InvalidDataException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.ProductDTO;
import com.backend.OnlineStore.model.mappers.ProductMapper;
import com.backend.OnlineStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return ProductMapper.INSTANCE.productToProductDTO(product);
    }


    public ProductDTO saveProduct(ProductDTO productDTO) {

        if (productDTO.getTitle() == null || productDTO.getTitle().isEmpty()) {
            throw new InvalidDataException("Product title is required");
        }
        if (productDTO.getPrice() < 0) {
            throw new InvalidDataException("Product price must be positive");
        }


        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        Product savedProduct = productRepository.save(product);


        return ProductMapper.INSTANCE.productToProductDTO(savedProduct);
    }

    public Page<ProductDTO> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMapper.INSTANCE::productToProductDTO);
    }

    public Optional<List<ProductDTO>> findProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .map(products -> products.stream()
                        .map(ProductMapper.INSTANCE::productToProductDTO)
                        .toList());
    }


    public Optional<List<ProductDTO>> searchProductsByTitle(String title) {
        return productRepository.findByTitleContaining(title)
                .map(products -> products.stream()
                        .map(ProductMapper.INSTANCE::productToProductDTO)
                        .toList());
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
