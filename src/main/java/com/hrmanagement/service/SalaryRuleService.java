//package com.hrmanagement.service;
//
//import com.hrmanagement.entities.SalaryRule;
//import com.hrmanagement.exception.NotFoundException;
//import com.hrmanagement.repositories.SalaryRuleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SalaryRuleService {
//
//    @Autowired
//    private SalaryRuleRepository salaryRuleRepository;
//
//    public List<SalaryRule> getAllSalaryRules() {
//        return salaryRuleRepository.findAll();
//    }
//
//    public SalaryRule getById(Integer id) {
//        return salaryRuleRepository.findById(id).orElseThrow(() -> new NotFoundException("not found with id: " + id));
//    }
//}
//
