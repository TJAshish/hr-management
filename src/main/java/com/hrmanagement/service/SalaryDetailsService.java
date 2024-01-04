package com.hrmanagement.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrmanagement.entities.SalaryDetails;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.repositories.SalaryDetailsRepository;

@Service
public class SalaryDetailsService {

    @Autowired
    private SalaryDetailsRepository salaryDetailsRepository;

    public List<SalaryDetails> getSalaryDetails() {
        return salaryDetailsRepository.findAll();
    }

    public SalaryDetails getSalaryDetailsById(Integer id) {
        return salaryDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee Salary Details with ID " + id + " not found"));
    }

    public SalaryDetails createSalaryDetails(SalaryDetails salaryDetails) {
        // Additional validation or business logic can be added here
        return salaryDetailsRepository.save(salaryDetails);
    }

    public SalaryDetails updateSalaryDetails(Integer id, SalaryDetails updatedSalaryDetails) {
        if (!salaryDetailsRepository.existsById(id)) {
            throw new NotFoundException("Employee Salary Details with ID " + id + " not found");
        }

        // Additional validation or business logic can be added here
        updatedSalaryDetails.setId(id);
        return salaryDetailsRepository.save(updatedSalaryDetails);
    }

    public void deleteSalaryDetails(Integer id) {
        if (!salaryDetailsRepository.existsById(id)) {
            throw new NotFoundException("Employee Salary Details with ID " + id + " not found");
        }

        salaryDetailsRepository.deleteById(id);
    }
}
