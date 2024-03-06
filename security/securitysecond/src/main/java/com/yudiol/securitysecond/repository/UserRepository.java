package com.yudiol.securitysecond.repository;

import com.yudiol.securitysecond.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    @Modifying
    @Query(value = "UPDATE users SET login_attempts = login_attempts + 1 WHERE username = :username",nativeQuery = true)
    void setLoginAttempts(String username);
}
