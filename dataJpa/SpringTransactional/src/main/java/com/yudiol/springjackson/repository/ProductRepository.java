package com.yudiol.springjackson.repository;

import com.yudiol.springjackson.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductId(Long productId);

    @Modifying
    @Query( value = "DELETE FROM products WHERE product_id = :productId", nativeQuery = true)
    void deleteByProductId(Long productId);
}
