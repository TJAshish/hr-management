package com.hrmanagement.controllers;

import com.hrmanagement.entities.Employees;    
import com.hrmanagement.service.EmployeesService;
import com.hrmanagement.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	private  EmployeesService employeesService;
	
	@Autowired
	private FileService fileService;

	@Value("${apis.image}")
	private String path;
	
	@PostMapping("/{clientId}")
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employee,
                                                    @PathVariable Integer clientId) {
        Employees createdEmployee = employeesService.createEmployee(employee, clientId);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
	@PutMapping("/{empId}/{clientId}")
    public ResponseEntity<Employees> updateEmployee(@Valid @RequestBody Employees updatedEmployee,
                                                   @PathVariable("empId") Integer empId,
                                                   @PathVariable("clientId") Integer clientId) {
        Employees updated = employeesService.updateEmployee(empId, clientId, updatedEmployee);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{empId}/{clientId}")
    public ResponseEntity<Employees> getEmployeeByIdAndClientId(@PathVariable("empId") Integer empId,
                                                               @PathVariable("clientId") Integer clientId) {
        Employees employee = employeesService.getEmployeeByIdAndClientId(empId, clientId);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all/{clientId}")
    public ResponseEntity<List<Employees>> getAllEmployees(
    		@PathVariable("clientId") Integer clientId) {
        	List<Employees> employees = employeesService.findByClientId(clientId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Page<Employees>> getAllEmployees(
            @PathVariable("clientId") Integer clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employees> employeesPage = employeesService.getAllEmployeesById(clientId, pageable);

        return ResponseEntity.ok(employeesPage);
    }

    @DeleteMapping("/{empId}/{clientId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("empId") Integer empId,
                                                     @PathVariable("clientId") Integer clientId) {
        employeesService.deleteEmployeeById(empId, clientId);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public Page<Employees> searchEmployees(@RequestParam("query") String query,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ){
    	Pageable pageable = PageRequest.of(page, size);
        return employeesService.searchEmployees(query, pageable);
        
    }
	// post image upload
	
			@PostMapping("/image/upload/{empId}/{clientId}")
			public ResponseEntity<Employees> uploadImage(
					@RequestParam ("image") MultipartFile image,
					@PathVariable Integer empId,
					@PathVariable Integer clientId
					)throws IOException
			{
				Employees emp = this.employeesService.getEmployeeByIdAndClientId(empId, clientId);
				String fileName = this.fileService.uploadImage(path, image);
				emp.setPhoto(fileName);
				Employees updateEmp = this.employeesService.updateEmployee(empId,clientId, emp);
				return new ResponseEntity<Employees>(updateEmp,HttpStatus.OK);
			}
			//method to server files
			
			@GetMapping(value ="/image/{imageName}")
			public void downloadImage(@Valid
					@PathVariable("imageName") String imageNmae,HttpServletResponse response
					)throws IOException {
				InputStream resource = this.fileService.getResource(path, imageNmae);
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				StreamUtils.copy(resource, response.getOutputStream());
			}
			//filter by department

			@GetMapping("/department/{departmentName}")
			    public List<Employees> findByDepartmentName(@PathVariable String departmentName ) {
			        return employeesService.findByDepartmentName(departmentName);
			    }
			


}
