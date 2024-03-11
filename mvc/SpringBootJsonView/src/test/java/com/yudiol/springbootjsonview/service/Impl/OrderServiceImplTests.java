package com.yudiol.springbootjsonview.service.Impl;

import com.yudiol.springbootjsonview.exception.errors.NotFoundException;
import com.yudiol.springbootjsonview.model.Order;
import com.yudiol.springbootjsonview.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;


    Order order = new Order();

    @BeforeEach
    public void setup() {
        order.setOrderId(1L);
        order.setName("Phone");
        order.setTotalAmount(22);
        order.setStatus("active");
    }

    @Test
    public void getById() {
        Long orderId = 1L;
//        Order mockOrder = new Order();
//        mockOrder.setOrderId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

//        Order result = orderService.getById(orderId);
//
//        assertNotNull(result);
//        assertEquals(orderId, result.getOrderId());
//
//        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void getById_WhenOrderNotFound() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.getById(orderId));

        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void deleteById() {
        Long orderId = 1L;

        orderService.deleteById(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    public void create() {
        Order mockOrder = new Order();

        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);

        Order result = orderService.create(mockOrder);

        assertNotNull(result);

        verify(orderRepository, times(1)).save(mockOrder);
    }

    @Test
    public void update() {
//        Long orderId = 1L;
//        Order existingOrder = new Order();
//        existingOrder.setOrderId(orderId);

        Order updatedOrder = new Order();
        updatedOrder.setName("Updated Order");

        when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

        Order result = orderService.update(order.getOrderId(), updatedOrder);

        assertNotNull(result);
        assertEquals("Updated Order", result.getName());

        verify(orderRepository, times(1)).findById(order.getOrderId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void update_WhenOrderNotFound() {
        Long orderId = 1L;
        Order updatedOrder = new Order();

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.update(orderId, updatedOrder));

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(any(Order.class));
    }
}