package com.yudiol.springjackson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "address")
    @JsonProperty("address")
    private String address;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    @Enumerated
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonProperty("customerId")
    private Customer customer;

    @ManyToMany(mappedBy = "orders")
    @JsonProperty("products")
    private List<Product> products;
}
