//package com.hrmanagement.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hrmanagement.entities.Department;
//import com.hrmanagement.exception.NotFoundException;
//import com.hrmanagement.repositories.DepartmentRepository;
//
//@Service
//public class DepartmentService {
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    public List<Department> getAllDepartments() {
//        return departmentRepository.findAll();
//    }
//
//    public Department getDepartmentById(Integer id) {
//        return departmentRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));
//    }
//
//}
