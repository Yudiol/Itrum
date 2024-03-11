package com.yudiol.springbootjsonview.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.yudiol.springbootjsonview.model.User;
import com.yudiol.springbootjsonview.model.Views;
import com.yudiol.springbootjsonview.service.UserService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class UserController {

    private final UserService userService;

    @GetMapping
    @JsonView(Views.UserSummary.class)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    @JsonView(Views.UserDetails.class)
    public User getUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        Checker.checkValidationErrors(bindingResult);
        return userService.create(user);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody @Valid User updatedUser, BindingResult bindingResult) {
        Checker.checkValidationErrors(bindingResult);
        return userService.update(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }
}
