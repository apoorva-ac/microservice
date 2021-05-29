package com.microservice.department.service;

import com.microservice.department.entity.Department;
import com.microservice.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public Department saveDepartment(Department department) {
        log.info("saveDepartment in DepartmentService");
        return repository.save(department);
    }

    public Department findDepartmentById(@PathVariable Long departmentId) {
        return repository.findDepartmentById(departmentId);
    }
}
