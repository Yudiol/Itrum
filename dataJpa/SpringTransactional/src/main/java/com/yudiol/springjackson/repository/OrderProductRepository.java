package com.yudiol.springjackson.repository;

import com.yudiol.springjackson.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query(value = "SELECT * FROM orders_products WHERE order_id = ?1", nativeQuery = true)
    Optional<List<OrderProduct>> findByOrderId(Long orderId);

    @Query(value = "SELECT * FROM orders_products WHERE product_id = ?1", nativeQuery = true)
    Optional<List<OrderProduct>> findByProductId(Long productId);

    @Modifying
    @Query( value = "DELETE FROM orders_products WHERE order_id = :orderId AND product_id IN :productIds", nativeQuery = true)
    void deleteAllByProductId(@Param("orderId") Long orderId, @Param("productIds") Set<Long> productIds);


}
