package com.yudiol.SpringProjection.model;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    @Value("#{target.department.name}")
    String getDepartmentName();

    String getPosition();
}
