package com.yudiol.springjackson.service.Impl;

import com.yudiol.springjackson.dto.RequestSaveOrder;
import com.yudiol.springjackson.dto.RequestUpdateOrder;
import com.yudiol.springjackson.dto.ResponseDto;
import com.yudiol.springjackson.exception.errors.BadRequestError;
import com.yudiol.springjackson.exception.errors.NotFoundException;
import com.yudiol.springjackson.model.Customer;
import com.yudiol.springjackson.model.Order;
import com.yudiol.springjackson.model.OrderProduct;
import com.yudiol.springjackson.model.Product;
import com.yudiol.springjackson.model.Status;
import com.yudiol.springjackson.repository.CustomerRepository;
import com.yudiol.springjackson.repository.OrderProductRepository;
import com.yudiol.springjackson.repository.OrderRepository;
import com.yudiol.springjackson.repository.ProductRepository;
import com.yudiol.springjackson.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    @Transactional(readOnly = true)
    public ResponseDto findByOrderId(Long orderId) {
        return orderRepository.findResponseDtoByOrderId(orderId)
                .orElseThrow(() -> new NotFoundException("Заказ с таким id не найден"));
    }

    @Transactional
    public Long create(RequestSaveOrder order) {

        Customer customer = customerRepository.findByCustomerId(order.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Клиент ", String.valueOf(order.getCustomerId())));
        List<Product> products = productRepository.findAllById(order.getProducts().keySet());

        int price = 0;
        Order newOrder = new Order();

        for (Product product : products) {
            long quantity = order.getProducts().get(product.getProductId());
            long quantityInStock = product.getQuantityInStock() - quantity;
            price += product.getPrice() * quantity;
            if (quantityInStock < 0) {
                throw new BadRequestError("Продукт " + product.getName() + " отсутствует на складе");
            }
            OrderProduct orderProduct = new OrderProduct(newOrder, product, quantity);
            newOrder.getProducts().add(orderProduct);
            product.getOrders().add(orderProduct);
            product.setQuantityInStock((int) quantityInStock);
        }
        newOrder.setAddress(order.getAddress());
        newOrder.setCustomer(customer);
        newOrder.setStatus(Status.created);
        newOrder.setPrice(price);
        return orderRepository.save(newOrder).getOrderId();
    }

    @Transactional
    public void update(Long orderId, RequestUpdateOrder requestUpdateOrder) {

        Customer customer = customerRepository.findByCustomerId(requestUpdateOrder.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Клиент ", String.valueOf(requestUpdateOrder.getCustomerId())));

        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NotFoundException("Заказ ", String.valueOf(orderId)));

        int price = 0;
        var productsInOldOrder = orderProductRepository.findByOrderId(orderId).get().stream().collect(Collectors.toMap(OrderProduct::getProduct, e -> e));
        List<Product> productsInUpdatedOrder = productRepository.findAllById(requestUpdateOrder.getProducts().keySet());
        Set<Product> result = new HashSet<>(productsInOldOrder.keySet());
        result.removeAll(new HashSet<>(productsInUpdatedOrder));

        for (Product product : result) {
            int quantityInStock = (int) (product.getQuantityInStock() + productsInOldOrder.get(product).getQuantity());
            product.setQuantityInStock(quantityInStock);
        }
        orderProductRepository.deleteAllByProductId(orderId, result.stream().map(Product::getProductId).collect(Collectors.toSet()));
        for (Product product : productsInUpdatedOrder) {
            long quantity = requestUpdateOrder.getProducts().get(product.getProductId());
            price += product.getPrice() * quantity;
            long quantityInOrder = productsInOldOrder.getOrDefault(product, null) == null ? 0L : productsInOldOrder.get(product).getQuantity();
            long quantityInStock = product.getQuantityInStock() + quantityInOrder - quantity;
            if (quantityInStock < 0) {
                throw new BadRequestError("Продукт " + product.getName() + " отсутствует на складе");
            }
            if (productsInOldOrder.containsKey(product)) {
                productsInOldOrder.get(product).setQuantity(quantity);
                order.getProducts().add(productsInOldOrder.get(product));
                product.getOrders().add(productsInOldOrder.get(product));
            } else {
                OrderProduct orderProduct = new OrderProduct(order, product, quantity);
                orderProductRepository.save(orderProduct);
                order.getProducts().add(orderProduct);
                product.getOrders().add(orderProduct);
            }
            product.setQuantityInStock((int) quantityInStock);
        }
        order.setAddress(requestUpdateOrder.getAddress());
        order.setCustomer(customer);
        order.setStatus(requestUpdateOrder.getStatus());
        order.setPrice(price);
        orderRepository.save(order);
    }

    @Transactional
    public void delete(Long orderId) {
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderId).get();
        if (orderProducts.size() == 0) {
            throw new NotFoundException("Заказ с таким id не найден");
        }
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.getProduct().setQuantityInStock(orderProduct.getProduct().getQuantityInStock() + (int) orderProduct.getQuantity());
        }
        orderRepository.delete(orderProducts.get(0).getOrder());
    }
}
