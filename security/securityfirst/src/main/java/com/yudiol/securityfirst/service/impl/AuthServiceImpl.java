package com.yudiol.securityfirst.service.impl;

import com.yudiol.securityfirst.JwtAuthenticationProvider;
import com.yudiol.securityfirst.dto.AuthenticationRequestDto;
import com.yudiol.securityfirst.dto.AuthenticationResponseDto;
import com.yudiol.securityfirst.service.AuthService;
import com.yudiol.securityfirst.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> createAuthToken(AuthenticationRequestDto auth) {
        authenticationManager.authenticate(jwtAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword())));
        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.status(201).body(new AuthenticationResponseDto(token));
    }
}
