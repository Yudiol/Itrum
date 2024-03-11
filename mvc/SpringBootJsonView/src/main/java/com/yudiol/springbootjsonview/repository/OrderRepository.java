package com.yudiol.springbootjsonview.repository;

import com.yudiol.springbootjsonview.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}