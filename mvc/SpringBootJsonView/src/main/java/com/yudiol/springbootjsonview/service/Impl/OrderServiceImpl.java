package com.yudiol.springbootjsonview.service.Impl;

import com.yudiol.springbootjsonview.exception.errors.NotFoundException;
import com.yudiol.springbootjsonview.model.Order;
import com.yudiol.springbootjsonview.repository.OrderRepository;
import com.yudiol.springbootjsonview.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order getById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Заказ с id = " + orderId + " не найден"));
    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Long orderId, Order updatedOrder) {

        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Заказ с id = " + orderId + " не найден"));
        if (existingOrder != null) {
            existingOrder.setName(updatedOrder.getName());
            existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
            existingOrder.setStatus(updatedOrder.getStatus());

            return orderRepository.save(existingOrder);
        }

        return null;

    }
}
