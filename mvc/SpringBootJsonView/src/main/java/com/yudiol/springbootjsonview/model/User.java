package com.yudiol.springbootjsonview.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserSummary.class)
    @Column(name = "user_id")

    private Long userId;

    @JsonView(Views.UserSummary.class)
    @Column(name = "name")
    @NotNull(message = "Имя пользователя должно быть заполнено")
    private String name;

    @JsonView(Views.UserDetails.class)
    @Column(name = "email")
    @Email(message = "Поле 'email' должно иметь формат почты. Пример Petr@mail.com")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonView(Views.UserDetails.class)
    private List<Order> orders;
}
