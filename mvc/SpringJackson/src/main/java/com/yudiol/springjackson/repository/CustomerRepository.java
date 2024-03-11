package com.yudiol.springjackson.repository;

import com.yudiol.springjackson.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer , Long> {


    Optional<Customer> findByCustomerId(Long customerId);
}
