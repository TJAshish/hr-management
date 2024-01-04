package com.hrmanagement.service;

import com.hrmanagement.entities.Employees;
import com.hrmanagement.exception.EmployeeNotFoundException;
import com.hrmanagement.repositories.EmployeesRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeesService {
	
	@Autowired
	private  EmployeesRepository employeesRepository;
	
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
	        existingEmployee.setEmpId(empId);
	        // Update other fields as needed
	        existingEmployee.setFullName(updatedEmployee.getFullName());
	        existingEmployee.setJoiningDate(updatedEmployee.getJoiningDate());
	        // Update other fields as needed

	        return employeesRepository.save(existingEmployee);
	    }

	 public boolean existsByIdAndClientId(Integer empId, Integer clientId) {
	        return employeesRepository.findByEmpIdAndClientId(empId, clientId) != null;
	    }

	    public void deleteEmployeeById(Integer empId, Integer clientId) {
	        if (!existsByIdAndClientId(empId, clientId)) {
	            throw new EmployeeNotFoundException("Employee not found for empId: " + empId + " and clientId: " + clientId);
	        }
	        employeesRepository.deleteByEmpIdAndClientId(empId, clientId);
	    }
	    public List<Employees> searchEmployeesByKeyword(String keyword) {
	        return employeesRepository.findByFullName(keyword);
	        // You can add more search methods for other fields if needed
	    }

}
