package com.hrmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrmanagement.entities.SalaryMode;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.repositories.SalaryModeRepository;

@Service
public class SalaryModeService {

    @Autowired
    private SalaryModeRepository salaryModeRepository;

    public List<SalaryMode> getAllSalaryModes() {
        return salaryModeRepository.findAll();
    }

    public SalaryMode getById(Integer id) {
        return salaryModeRepository.findById(id).orElseThrow(() -> new NotFoundException("not found with id: " + id));
    }
 }
