package com.yudiol.springjackson.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yudiol.springjackson.dto.RequestCreateOrder;
import com.yudiol.springjackson.model.Order;
import com.yudiol.springjackson.model.Product;
import com.yudiol.springjackson.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @GetMapping("/{orderId}")
    public String get(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String[] pathSegments = requestURI.split("/");
        Long productId = Long.parseLong(pathSegments[pathSegments.length - 1]);
        Order order = orderService.get(productId);
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Произошла ошибка в процессе преобразования объекта в json";
        }
    }

    @PostMapping
    public Long create(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
            char[] charBuffer = new char[1024];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        }
        RequestCreateOrder order = objectMapper.readValue(stringBuilder.toString(), RequestCreateOrder.class);
        return orderService.create(order);
    }


}
