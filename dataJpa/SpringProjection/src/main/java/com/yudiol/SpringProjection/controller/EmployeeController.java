package com.yudiol.SpringProjection.controller;

import com.yudiol.SpringProjection.dto.RequestEmployee;
import com.yudiol.SpringProjection.model.Employee;
import com.yudiol.SpringProjection.model.EmployeeProjection;
import com.yudiol.SpringProjection.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public EmployeeProjection get(@PathVariable Long employeeId) {
        return employeeService.findByEmployeeId(employeeId);
    }

    @PostMapping
    public void create(@RequestBody Employee employee) {
        employeeService.create(employee);
    }

    @PatchMapping("/{employeeId}")
    public void update(@PathVariable Long employeeId, @RequestBody RequestEmployee employee) {
        employeeService.update(employeeId, employee);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Long employeeId) {
        employeeService.delete(employeeId);
    }
}
