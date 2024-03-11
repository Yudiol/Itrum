package com.yudiol.springjackson.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
public class RequestCreateOrder {

    private Long customerId;
    private String address;
    private Map<Long, Long> products;
}
