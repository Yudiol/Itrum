package com.yudiol.springjackson.dto;

import com.yudiol.springjackson.model.Customer;
import com.yudiol.springjackson.model.Status;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface ResponseDto {
    Long getOrderId();

    String getAddress();

    int getPrice();

    Status getStatus();

    Customer getCustomer();

    List<OrderProduct> getProducts();

    interface OrderProduct {
        @Value("#{target.product.productId}")
        long getProductId();

        @Value("#{target.product.name}")
        String getName();

        @Value("#{target.product.description}")
        String getDescription();

        @Value("#{target.product.price}")
        int getPrice();

        @Value("#{target.quantity}")
        long getQuantity();
    }
}
