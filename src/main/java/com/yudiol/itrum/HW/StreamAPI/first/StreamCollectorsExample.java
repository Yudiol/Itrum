package com.yudiol.itrum.HW.StreamAPI.first;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, Double> map = orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.reducing(0.0, Order::getCost, Double::sum)));
        map.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(3).forEach(System.out::println);
    }
}