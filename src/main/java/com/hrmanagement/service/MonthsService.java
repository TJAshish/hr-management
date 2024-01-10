package com.hrmanagement.service;

import com.hrmanagement.entities.Months;
import com.hrmanagement.repositories.MonthsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthsService {

    @Autowired
    private MonthsRepository monthsRepository;

    public List<Months> getAllMonths() {
        return monthsRepository.findAll();
    }

    public Months getMonthById(Integer id) {
        return monthsRepository.findById(id).orElse(null);
    }
}

