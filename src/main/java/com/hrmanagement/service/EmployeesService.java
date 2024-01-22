package com.hrmanagement.service;

import com.hrmanagement.entities.Employees;
import com.hrmanagement.exception.EmployeeNotFoundException;
import com.hrmanagement.repositories.EmployeesRepository;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeesService {
	
	@Autowired
	private  EmployeesRepository employeesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Employees createEmployee(Employees employee, Integer clientId) {
        employee.setClientId(clientId);
        return employeesRepository.save(employee);
    }

	public Employees getEmployeeByIdAndClientId(Integer empId, Integer clientId) {
        return employeesRepository.findByEmpIdAndClientId(empId, clientId);
    }

	public Page<Employees> getAllEmployeesById(Integer clientId, Pageable pageable) {
        return employeesRepository.findByClientId(clientId, pageable);
    }

	public Employees updateEmployee(Integer empId, Integer clientId, Employees updatedEmployee) {
        Employees existingEmployee = employeesRepository.findByEmpIdAndClientId(empId, clientId);

        if (existingEmployee == null) {
            throw new EmployeeNotFoundException("Employee not found for empId: " + empId + " and clientId: " + clientId);
        }

        // Use ModelMapper to update fields
        modelMapper.map(updatedEmployee, existingEmployee);

        return employeesRepository.save(existingEmployee);
    }

	 public boolean existsByIdAndClientId(Integer empId, Integer clientId) {
	        return employeesRepository.findByEmpIdAndClientId(empId, clientId) != null;
	    }

	 	@Transactional
	    public void deleteEmployeeById(Integer empId, Integer clientId) {
	        if (!existsByIdAndClientId(empId, clientId)) {
	            throw new EmployeeNotFoundException("Employee not found for empId: " + empId + " and clientId: " + clientId);
	        }
	        employeesRepository.deleteByEmpIdAndClientId(empId, clientId);
	    }

	 	  public Page<Employees> searchEmployees(String query , Pageable pageable) {
	 		 return employeesRepository.searchEmployees(query, pageable);
	 	         
	 	    }
	 	 public List<Employees> findByClientId(Integer clientId) {
	         return employeesRepository.findByClientId(clientId);
	     }
	 	 

	    public List<Employees> findByDepartmentName(String departmentName) {
	        List<Employees> emp =this.employeesRepository.findByDepartmentName(departmentName);
	        return emp;
	    }

}
