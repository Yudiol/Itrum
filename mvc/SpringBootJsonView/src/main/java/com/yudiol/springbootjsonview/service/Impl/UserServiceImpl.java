package com.yudiol.springbootjsonview.service.Impl;

import com.yudiol.springbootjsonview.exception.errors.NotFoundException;
import com.yudiol.springbootjsonview.model.User;
import com.yudiol.springbootjsonview.repository.UserRepository;
import com.yudiol.springbootjsonview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь", String.valueOf(userId)));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь", String.valueOf(userId)));
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
