package com.hrmanagement.repositories;

import com.hrmanagement.entities.Employees; 

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
	
	List<Employees> findByFullName(String keyword);
	Employees findByEmpIdAndClientId(Integer empId, Integer clientId);
	Employees findByClientId(Integer clientId);
	Page<Employees> findByClientId(Integer clientId, Pageable pageable);
	 void deleteByEmpIdAndClientId(Integer empId, Integer clientId);
}
