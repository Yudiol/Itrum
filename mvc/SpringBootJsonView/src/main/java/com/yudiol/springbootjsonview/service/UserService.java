package com.yudiol.springbootjsonview.service;

import com.yudiol.springbootjsonview.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long userId);

    User create(User user);

    User update(Long userId,User user);

    void deleteById( Long userId);

}
