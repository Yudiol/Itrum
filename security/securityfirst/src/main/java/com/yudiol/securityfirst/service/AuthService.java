package com.yudiol.securityfirst.service;

import com.yudiol.securityfirst.dto.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> createAuthToken(AuthenticationRequestDto authenticationRequestDto);
}
