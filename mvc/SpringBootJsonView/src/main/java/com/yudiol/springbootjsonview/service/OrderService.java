package com.yudiol.springbootjsonview.service;

import com.yudiol.springbootjsonview.model.Order;

public interface OrderService {
    Order getById(Long orderId);

    void deleteById(Long orderId);

    Order create(Order order);

    Order update(Long orderId, Order order);
}
