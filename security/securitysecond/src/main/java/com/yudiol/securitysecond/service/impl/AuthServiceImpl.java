package com.yudiol.securitysecond.service.impl;

import com.yudiol.securitysecond.dto.AuthenticationRequestDto;
import com.yudiol.securitysecond.dto.AuthenticationResponseDto;
import com.yudiol.securitysecond.service.AuthService;
import com.yudiol.securitysecond.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public ResponseEntity<?> createAuthToken(AuthenticationRequestDto auth) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        log.info("Пользователь вошёл в систему");
        return ResponseEntity.status(201).body(new AuthenticationResponseDto(token));
    }
}
