package com.yudiol.SpringProjection.repository;

import com.yudiol.SpringProjection.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);

    Optional<Department> findByDepartmentId(Long departmentId);
}
