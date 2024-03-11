package com.yudiol.springjackson.controller;

import com.yudiol.springjackson.dto.RequestSaveOrder;
import com.yudiol.springjackson.dto.RequestUpdateOrder;
import com.yudiol.springjackson.dto.ResponseDto;
import com.yudiol.springjackson.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/orders")
@ResponseStatus(HttpStatus.OK)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseDto findOrderId(@PathVariable Long orderId) {
        return orderService.findByOrderId(orderId);
    }

    @PatchMapping("/{orderId}")
    public void update(@PathVariable Long orderId, @RequestBody RequestUpdateOrder requestUpdateOrder){
        orderService.update(orderId, requestUpdateOrder);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long create(@RequestBody RequestSaveOrder order) {
        return orderService.create(order);
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable Long orderId) {
        orderService.delete(orderId);
    }


}
