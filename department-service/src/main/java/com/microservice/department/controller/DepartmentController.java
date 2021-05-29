package com.microservice.department.controller;

import com.microservice.department.entity.Department;
import com.microservice.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        log.info("In save department");
        return service.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable Long id) {
        log.info("In findDepartmentById ");
        return service.findDepartmentById(id);
    }

}
