package com.hrmanagement.controllers;

import com.hrmanagement.entities.SalaryRule;
import com.hrmanagement.service.SalaryRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary-rules")
public class SalaryRuleController {

    @Autowired
    private SalaryRuleService salaryRuleService;

    @GetMapping
    public ResponseEntity<List<SalaryRule>> getAllSalaryRules() {
        List<SalaryRule> salaryRules = salaryRuleService.getAllSalaryRules();
        return new ResponseEntity<>(salaryRules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryRule> getById(@PathVariable Integer id) {
        SalaryRule salaryRule = salaryRuleService.getById(id);

        if (salaryRule != null) {
            return new ResponseEntity<>(salaryRule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

