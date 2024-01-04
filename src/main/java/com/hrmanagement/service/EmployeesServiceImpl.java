//package com.hrmanagement.service;
//
//
//import com.hrmanagement.entities.Employees;
//import com.hrmanagement.exception.NotFoundException;
//import com.hrmanagement.repositories.EmployeesRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeesServiceImpl implements EmployeesService {
//
//    @Autowired
//    private EmployeesRepository employeeRepository;
//
//    @Override
//    public List<Employees> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    @Override
//    public Employees getEmployeeById(Integer id) {
//        Optional<Employees> optionalEmployee = employeeRepository.findById(id);
//        return optionalEmployee.orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
//    }
//
//    @Override
//    public Employees saveEmployee(Employees employee) {
//        // Add validation logic if needed
//        return employeeRepository.save(employee);
//    }
//
//	@Override
//	public void deleteEmployee(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
//
