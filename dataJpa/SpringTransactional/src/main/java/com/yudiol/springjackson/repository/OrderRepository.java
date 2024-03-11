package com.yudiol.springjackson.repository;

import com.yudiol.springjackson.dto.ResponseDto;
import com.yudiol.springjackson.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderId(Long orderId);

    Optional<ResponseDto> findResponseDtoByOrderId(Long orderId);
}
