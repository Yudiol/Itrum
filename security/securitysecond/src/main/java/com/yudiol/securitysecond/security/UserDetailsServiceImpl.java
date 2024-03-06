package com.yudiol.securitysecond.security;

import com.yudiol.securitysecond.model.User;
import com.yudiol.securitysecond.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(String.format("Пользователь с username = '%s' не найден", username));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь с username = '%s' не найден", username)));
        return new UserDetailsImpl(user);
    }
}
