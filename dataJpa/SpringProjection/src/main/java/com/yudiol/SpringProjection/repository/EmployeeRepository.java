package com.yudiol.SpringProjection.repository;

import com.yudiol.SpringProjection.model.Employee;
import com.yudiol.SpringProjection.model.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , Long> {

    Optional<Employee> findByEmployeeId(Long employeeId);

    Optional<EmployeeProjection> findEmployeeProjectionByEmployeeId(Long employeeId);


}
