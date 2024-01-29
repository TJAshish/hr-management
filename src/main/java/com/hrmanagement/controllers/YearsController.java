//package com.hrmanagement.controllers;
//
//import com.hrmanagement.entities.Years;
//import com.hrmanagement.service.YearsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/years")
//public class YearsController {
//
//    @Autowired
//    private YearsService yearsService;
//
//    @GetMapping
//    public ResponseEntity<List<Years>> getAllYears() {
//        List<Years> yearsList = yearsService.getAllYears();
//        return ResponseEntity.ok(yearsList);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Years> getYearById(@PathVariable Integer id) {
//        Years year = yearsService.getYearById(id);
//        if (year != null) {
//            return ResponseEntity.ok(year);
//        } else {
//            // You can customize the response for a not found case
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//}
