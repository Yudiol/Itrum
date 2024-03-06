package com.yudiol.securitysecond.controller;

import com.yudiol.securitysecond.dto.AuthenticationRequestDto;
import com.yudiol.securitysecond.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return authService.createAuthToken(authenticationRequestDto);
    }
}
