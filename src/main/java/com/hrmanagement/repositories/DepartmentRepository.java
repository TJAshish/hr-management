package com.hrmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrmanagement.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
