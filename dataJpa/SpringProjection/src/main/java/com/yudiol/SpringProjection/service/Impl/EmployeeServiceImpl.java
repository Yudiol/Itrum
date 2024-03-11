package com.yudiol.SpringProjection.service.Impl;

import com.yudiol.SpringProjection.dto.RequestEmployee;
import com.yudiol.SpringProjection.exception.errors.NotFoundException;
import com.yudiol.SpringProjection.model.Department;
import com.yudiol.SpringProjection.model.Employee;
import com.yudiol.SpringProjection.model.EmployeeProjection;
import com.yudiol.SpringProjection.repository.DepartmentRepository;
import com.yudiol.SpringProjection.repository.EmployeeRepository;
import com.yudiol.SpringProjection.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public EmployeeProjection findByEmployeeId(Long employeeId) {
        return employeeRepository.findEmployeeProjectionByEmployeeId(employeeId)
                .orElseThrow(() -> new NotFoundException("Сотрудник", String.valueOf(employeeId)));
    }

    @Transactional
    public void create(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void update(Long employeeId, RequestEmployee updatedEmployee) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new NotFoundException("Сотрудник", String.valueOf(employeeId)));
        Department department = departmentRepository.findByName(updatedEmployee.getDepartment())
                .orElseThrow(() -> new NotFoundException("Департамент с названием " + updatedEmployee.getDepartment() + " не найден"));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setPosition(updatedEmployee.getPosition());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }

    @Transactional
    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
