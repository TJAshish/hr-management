package com.hrmanagement.service;

import com.hrmanagement.entities.Years;
import com.hrmanagement.repositories.YearsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearsService {

    @Autowired
    private YearsRepository yearsRepository;

    public List<Years> getAllYears() {
        return yearsRepository.findAll();
    }

    public Years getYearById(Integer id) {
        return yearsRepository.findById(id).orElse(null);
    }

    }
