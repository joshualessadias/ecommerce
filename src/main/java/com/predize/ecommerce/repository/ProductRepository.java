package com.predize.ecommerce.repository;

import com.predize.ecommerce.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long productId);
    Optional<Product> findById(Long id);
}
