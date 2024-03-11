package com.yudiol.springjackson.service;


import com.yudiol.springjackson.dto.RequestCreateOrder;
import com.yudiol.springjackson.model.Order;

public interface OrderService {
    Order get(Long orderId);
    Long create(RequestCreateOrder order);
}
