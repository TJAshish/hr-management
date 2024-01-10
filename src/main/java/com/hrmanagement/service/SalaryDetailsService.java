package com.hrmanagement.service;

import com.hrmanagement.entities.SalaryDetails;
import com.hrmanagement.exception.SalaryDetailsNotFoundException;
import com.hrmanagement.repositories.SalaryDetailsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SalaryDetailsService {

    @Autowired
    private SalaryDetailsRepository salaryDetailsRepository;
    
    @Autowired
	private ModelMapper modelMapper;

    public SalaryDetails createSalaryDetails(SalaryDetails salaryDetails, Integer clientId) {
        salaryDetails.setClientId(clientId);
        return salaryDetailsRepository.save(salaryDetails);
    }

    public SalaryDetails getSalaryDetailsByIdAndClientId(Integer sdId, Integer clientId) {
        return salaryDetailsRepository.findBySdIdAndClientId(sdId, clientId)
                .orElseThrow(() -> new SalaryDetailsNotFoundException("SalaryDetails not found for sdId: " + sdId + " and clientId: " + clientId));
    }

    public Page<SalaryDetails> getAllSalaryDetailsByClientId(Integer clientId, Pageable pageable) {
        return salaryDetailsRepository.findByClientId(clientId, pageable);
    }

    public SalaryDetails updateSalaryDetails(Integer sdId, Integer clientId, SalaryDetails updatedSalaryDetails) {
        SalaryDetails existingSalaryDetails = salaryDetailsRepository.findBySdIdAndClientId(sdId, clientId)
                .orElseThrow(() -> new SalaryDetailsNotFoundException("SalaryDetails not found for sdId: " + sdId + " and clientId: " + clientId));

        // Use ModelMapper to update fields
        modelMapper.map(updatedSalaryDetails, existingSalaryDetails);

        return salaryDetailsRepository.save(existingSalaryDetails);
    }


    public boolean existsByIdAndClientId(Integer sdId, Integer clientId) {
        return salaryDetailsRepository.existsBySdIdAndClientId(sdId, clientId);
    }

    @Transactional
    public void deleteSalaryDetailsByIdAndClientId(Integer sdId, Integer clientId) {
        if (!existsByIdAndClientId(sdId, clientId)) {
            throw new SalaryDetailsNotFoundException("SalaryDetails not found for sdId: " + sdId + " and clientId: " + clientId);
        }
        salaryDetailsRepository.deleteBySdIdAndClientId(sdId, clientId);
    }
}
