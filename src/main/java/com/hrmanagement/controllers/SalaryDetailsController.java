package com.hrmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrmanagement.entities.SalaryDetails;
import com.hrmanagement.service.SalaryDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/salary-details")
public class SalaryDetailsController {

    @Autowired
    private SalaryDetailsService salaryDetailsService;

    @GetMapping
    public ResponseEntity<List<SalaryDetails>> getAllSalaryDetails() {
        List<SalaryDetails> salaryDetailsList = salaryDetailsService.getSalaryDetails();
        return new ResponseEntity<>(salaryDetailsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryDetails> getSalaryDetailsById(@PathVariable Integer id) {
        SalaryDetails salaryDetails = salaryDetailsService.getSalaryDetailsById(id);
        return new ResponseEntity<>(salaryDetails, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SalaryDetails> createSalaryDetails(@RequestBody SalaryDetails salaryDetails) {
        SalaryDetails createdSalaryDetails = salaryDetailsService.createSalaryDetails(salaryDetails);
        return new ResponseEntity<>(createdSalaryDetails, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaryDetails> updateSalaryDetails(
            @PathVariable Integer id,
            @RequestBody SalaryDetails updatedSalaryDetails) {
        SalaryDetails updatedDetails = salaryDetailsService.updateSalaryDetails(id, updatedSalaryDetails);
        return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalaryDetails(@PathVariable Integer id) {
        salaryDetailsService.deleteSalaryDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

