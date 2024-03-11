package com.yudiol.springjackson.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders_products")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne()
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;


    @Column(name = "quantity")
    private long quantity;

    public OrderProduct(Order order, Product product, long quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}