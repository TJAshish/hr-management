package com.hrmanagement.service;

import com.hrmanagement.entities.LeaveDetails;
import com.hrmanagement.entities.StudentDoc;
import com.hrmanagement.exception.EmployeeNotFoundException;
import com.hrmanagement.repositories.StudentDocRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentDocService {

	@Autowired
    private StudentDocRepository studentDocRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public StudentDoc create(StudentDoc studentDoc, Integer clientId) {
		studentDoc.setClientId(clientId);
        return studentDocRepository.save(studentDoc);
    }
    

    public Page<StudentDoc> getStudentDocsByClientId(Integer clientId, Pageable pageable) {
        return studentDocRepository.findByClientId(clientId, pageable);
    }

    public StudentDoc getStudentDocByIdAndClientId(Integer id, Integer clientId) {
        return studentDocRepository.findByIdAndClientId(id, clientId);
    }

    public StudentDoc update(Integer id, Integer clientId, StudentDoc updated) {
    	StudentDoc existingStd = studentDocRepository.findByIdAndClientId(id, clientId);

        if (existingStd == null) {
            throw new EmployeeNotFoundException("not found for empId: " + id + " and clientId: " + clientId);
        }

        // Use ModelMapper to update fields
        modelMapper.map(updated, existingStd);

        return studentDocRepository.save(existingStd);
    }

    @Transactional
    public void deleteStudentDocByIdAndClientId(Integer id, Integer clientId) {
        studentDocRepository.deleteByIdAndClientId(id, clientId);
    }
    public Page<StudentDoc> searchDetails(String query, Pageable pageable) {
    	return studentDocRepository.searchDetails(query, pageable);
         
    }
    
}
