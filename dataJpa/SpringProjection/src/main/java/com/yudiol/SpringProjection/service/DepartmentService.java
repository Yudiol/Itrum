package com.yudiol.SpringProjection.service;

import com.yudiol.SpringProjection.model.Department;

public interface DepartmentService {

    Department findByDepartmentId(Long departmentId);

    void create(Department department);

    void update(Long departmentId, Department department);

    void delete(Long departmentId);
}
