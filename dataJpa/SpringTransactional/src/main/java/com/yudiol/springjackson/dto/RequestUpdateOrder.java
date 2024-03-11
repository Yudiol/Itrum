package com.yudiol.springjackson.dto;

import com.yudiol.springjackson.model.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
public class RequestUpdateOrder {
    private Long customerId;
    private String address;
    private Status status;
    private Map<Long, Long> products;
}
