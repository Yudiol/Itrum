package com.yudiol.springjackson.service;


import com.yudiol.springjackson.dto.RequestSaveOrder;
import com.yudiol.springjackson.dto.RequestUpdateOrder;
import com.yudiol.springjackson.dto.ResponseDto;

public interface OrderService {
    ResponseDto findByOrderId(Long orderId);

    Long create(RequestSaveOrder order);

    void update(Long orderId, RequestUpdateOrder requestUpdateOrder);

    void delete(Long orderId);
}
