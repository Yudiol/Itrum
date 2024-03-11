package com.yudiol.SpringProjection.service.Impl;

import com.yudiol.SpringProjection.exception.errors.NotFoundException;
import com.yudiol.SpringProjection.model.Department;
import com.yudiol.SpringProjection.repository.DepartmentRepository;
import com.yudiol.SpringProjection.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Department findByDepartmentId(Long departmentId) {
        return departmentRepository.findByDepartmentId(departmentId)
                .orElseThrow(() -> new NotFoundException("Департамент", String.valueOf(departmentId)));
    }

    @Transactional
    public void create(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public void update(Long departmentId, Department updatedDepartment) {
        Department department = departmentRepository.findByDepartmentId(departmentId)
                .orElseThrow(() -> new NotFoundException("Департамент", String.valueOf(departmentId)));

        department.setName(updatedDepartment.getName());
        departmentRepository.save(department);
    }

    @Transactional
    public void delete(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
