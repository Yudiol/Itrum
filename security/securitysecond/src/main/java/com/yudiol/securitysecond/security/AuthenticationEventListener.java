package com.yudiol.securitysecond.security;

import com.yudiol.securitysecond.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationEventListener {

    private final UserRepository userRepository;

    @EventListener
    @Transactional
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        log.info("Введён не верный пароль");
        String username = (String) event.getAuthentication().getPrincipal();
        userRepository.setLoginAttempts(username);
    }

}