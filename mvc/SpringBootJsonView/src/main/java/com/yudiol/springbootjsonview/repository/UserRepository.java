package com.yudiol.springbootjsonview.repository;

import com.yudiol.springbootjsonview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}