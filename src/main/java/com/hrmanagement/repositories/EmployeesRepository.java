package com.hrmanagement.repositories;

import com.hrmanagement.entities.Employees; 

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
	
	Employees findByEmpIdAndClientId(Integer empId, Integer clientId);
	List<Employees> findByClientId(Integer clientId);
	Page<Employees> findByClientId(Integer clientId, Pageable pageable);
	 void deleteByEmpIdAndClientId(Integer empId, Integer clientId);
	 @Query("SELECT e FROM Employees e WHERE " +
	            "e.fullName LIKE CONCAT('%',:query, '%')" +
	            "Or e.email LIKE CONCAT('%', :query, '%')"
	            +
	            "Or e.mobile LIKE CONCAT('%', :query, '%')"
	            +
	            "Or e.userName LIKE CONCAT('%', :query, '%')"
	            +
	            "Or e.departmentId LIKE CONCAT('%', :query, '%')"
	            +
	            "Or e.code LIKE CONCAT('%', :query, '%')")
	    List<Employees> searchEmployees(String query);
}
