package com.yudiol.SpringProjection.controller;

import com.yudiol.SpringProjection.model.Department;
import com.yudiol.SpringProjection.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{departmentId}")
    public Department get(@PathVariable Long departmentId) {
        return departmentService.findByDepartmentId(departmentId);
    }

    @PostMapping
    public void create(@RequestBody Department department) {
        departmentService.create(department);
    }

    @PatchMapping("/{departmentId}")
    public void update(@PathVariable Long departmentId, @RequestBody Department department) {
        departmentService.update(departmentId, department);
    }

    @DeleteMapping("/{departmentId}")
    public void delete(@PathVariable Long departmentId) {
        departmentService.delete(departmentId);
    }
}
