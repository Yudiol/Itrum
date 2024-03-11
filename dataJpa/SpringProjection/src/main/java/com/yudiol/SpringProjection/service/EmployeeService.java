package com.yudiol.SpringProjection.service;

import com.yudiol.SpringProjection.dto.RequestEmployee;
import com.yudiol.SpringProjection.model.Employee;
import com.yudiol.SpringProjection.model.EmployeeProjection;

public interface EmployeeService {

    EmployeeProjection findByEmployeeId(Long employeeId);

    void create(Employee employee);

    void update(Long employeeId, RequestEmployee employee);

    void delete(Long employeeId);
}
