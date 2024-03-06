package com.yudiol.securitysecond.service;

import com.yudiol.securitysecond.dto.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> createAuthToken(AuthenticationRequestDto authenticationRequestDto);
}
