package com.hrmanagement.controllers;

import com.hrmanagement.entities.SalaryDetails;
import com.hrmanagement.service.SalaryDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary-details")
public class SalaryDetailsController {

    @Autowired
    private SalaryDetailsService salaryDetailsService;

    @PostMapping("/{clientId}")
    public ResponseEntity<SalaryDetails> createSalaryDetails(@RequestBody SalaryDetails salaryDetails, @PathVariable Integer clientId) {
        SalaryDetails createdSalaryDetails = salaryDetailsService.createSalaryDetails(salaryDetails, clientId);
        return new ResponseEntity<>(createdSalaryDetails, HttpStatus.CREATED);
    }

    @GetMapping("/{sdId}/{clientId}")
    public ResponseEntity<SalaryDetails> getSalaryDetailsByIdAndClientId(@PathVariable Integer sdId, @PathVariable Integer clientId) {
        SalaryDetails salaryDetails = salaryDetailsService.getSalaryDetailsByIdAndClientId(sdId, clientId);
        return ResponseEntity.ok(salaryDetails);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Page<SalaryDetails>> getAllSalaryDetailsByClientId(@PathVariable("clientId") Integer clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size); {
        Page<SalaryDetails> salaryDetailsPage = salaryDetailsService.getAllSalaryDetailsByClientId(clientId, pageable);
        return ResponseEntity.ok(salaryDetailsPage);
    }
    }

    @PutMapping("/{sdId}/{clientId}")
    public ResponseEntity<SalaryDetails> updateSalaryDetails(@PathVariable Integer sdId, @PathVariable Integer clientId, @RequestBody SalaryDetails updatedSalaryDetails) {
        SalaryDetails updatedDetails = salaryDetailsService.updateSalaryDetails(sdId, clientId, updatedSalaryDetails);
        return ResponseEntity.ok(updatedDetails);
    }

    @DeleteMapping("/{sdId}/{clientId}")
    public ResponseEntity<String> deleteSalaryDetailsByIdAndClientId(@PathVariable Integer sdId, @PathVariable Integer clientId) {
        salaryDetailsService.deleteSalaryDetailsByIdAndClientId(sdId, clientId);
        return ResponseEntity.ok("SalaryDetails deleted successfully");
    }
}
