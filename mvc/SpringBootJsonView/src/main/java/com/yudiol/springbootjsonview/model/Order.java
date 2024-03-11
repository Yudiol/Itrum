package com.yudiol.springbootjsonview.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserDetails.class)
    @Column(name = "order_id")
    private Long orderId;

    @JsonView(Views.UserDetails.class)
    @Column(name = "name")
    @NotNull(message = "Название товара не должно быть пустым")
    private String name;

    @JsonView(Views.UserDetails.class)
    @Column(name = "total_amount")
    @Min(value = 0, message = "Количество товара должно быть не меньше нуля")
    private int totalAmount;

    @JsonView(Views.UserDetails.class)
    @Column(name = "status")
    @NotNull(message = "Статус товара не должно быть пустым")
    private String status;

    @ManyToOne
    @JoinColumn(name = "foreign_user_id")
    private User user;
}
