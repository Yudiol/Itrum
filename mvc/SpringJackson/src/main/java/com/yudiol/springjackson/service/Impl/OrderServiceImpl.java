package com.yudiol.springjackson.service.Impl;

import com.yudiol.springjackson.dto.RequestCreateOrder;
import com.yudiol.springjackson.exception.errors.NotFoundException;
import com.yudiol.springjackson.model.Customer;
import com.yudiol.springjackson.model.Order;
import com.yudiol.springjackson.model.Product;
import com.yudiol.springjackson.model.Status;
import com.yudiol.springjackson.repository.CustomerRepository;
import com.yudiol.springjackson.repository.OrderRepository;
import com.yudiol.springjackson.repository.ProductRepository;
import com.yudiol.springjackson.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Order get(Long orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NotFoundException("Заказ с таким id не найден"));
    }

    @Transactional
    public Long create(RequestCreateOrder requestCreateOrder) {

        Customer customer = customerRepository.findByCustomerId(requestCreateOrder.getCustomerId())
                .orElseThrow(() -> new NotFoundException(String.format("Клиент с id= '%s' не найден", requestCreateOrder.getCustomerId())));
        var products = productRepository.findAllById(requestCreateOrder.getProducts().keySet());

        var p = products.stream().collect(Collectors.groupingBy(Product::getProductId, Collectors.averagingInt(Product::getPrice)));

        double price = p.entrySet().stream()
                .mapToDouble(e -> requestCreateOrder.getProducts().get(e.getKey()) * e.getValue())
                .sum();

        Order order = new Order();
        order.setStatus(Status.active);
        order.setAddress(requestCreateOrder.getAddress());
        order.setCustomer(customer);
        order.setProducts(products);
        order.setPrice((int) price);
        products.forEach(e -> e.getOrders().add(order));

        return orderRepository.save(order).getOrderId();
    }
}
