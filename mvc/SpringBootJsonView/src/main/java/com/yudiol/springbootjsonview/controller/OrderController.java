package com.yudiol.springbootjsonview.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yudiol.springbootjsonview.model.Order;
import com.yudiol.springbootjsonview.model.Views;
import com.yudiol.springbootjsonview.service.OrderService;
import com.yudiol.springbootjsonview.util.Checker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/orders")
@ResponseStatus(HttpStatus.OK)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    @JsonView(Views.UserDetails.class)
    public Order getById(@PathVariable Long orderId) {
        return orderService.getById(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order create(@RequestBody @Valid Order order, BindingResult bindingResult) {
        Checker.checkValidationErrors(bindingResult);
        return orderService.create(order);
    }

    @PatchMapping("/{orderId}")
    public Order update(@PathVariable Long orderId, @RequestBody @Valid Order order,BindingResult bindingResult) {
        Checker.checkValidationErrors(bindingResult);
        return orderService.update(orderId, order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteById(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
    }
}
