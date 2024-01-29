//package com.hrmanagement.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hrmanagement.entities.SalaryMode;
//import com.hrmanagement.service.SalaryModeService;
//
//@RestController
//@RequestMapping("/salary-modes")
//public class SalaryModeController {
//
//    @Autowired
//    private SalaryModeService salaryModeService;
//
//    @GetMapping
//    public ResponseEntity<List<SalaryMode>> getAllSalaryModes() {
//        List<SalaryMode> salaryModes = salaryModeService.getAllSalaryModes();
//        return ResponseEntity.ok(salaryModes);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<SalaryMode> getSalaryModeById(@PathVariable Integer id) {
//        SalaryMode salaryMode = salaryModeService.getById(id);
//        if (salaryMode == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(salaryMode);
//    }
//}
