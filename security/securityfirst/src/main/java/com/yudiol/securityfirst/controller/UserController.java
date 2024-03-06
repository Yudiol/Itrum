package com.yudiol.securityfirst.controller;

import com.yudiol.securityfirst.dto.AuthenticationRequestDto;
import com.yudiol.securityfirst.model.User;
import com.yudiol.securityfirst.repository.UserRepository;
import com.yudiol.securityfirst.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserRepository userRepository;
    private final AuthService authService;

    @GetMapping("/public/{id}")
    public User get(@PathVariable Long id) {
        return userRepository.findByUserId(id).orElse(null);
    }

    @GetMapping("/secured")
    public String getSecured(){
        return "secured";
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        System.out.println("POST/auth");
        return authService.createAuthToken(authenticationRequestDto);
    }
}
