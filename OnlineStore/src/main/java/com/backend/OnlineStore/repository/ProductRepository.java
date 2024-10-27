package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);


    List<Product> findByTitleContaining(String title);

    Page<Product> findAll(Pageable pageable);
}
