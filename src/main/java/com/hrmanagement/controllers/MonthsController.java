//package com.hrmanagement.controllers;
//
//import com.hrmanagement.entities.Months;
//import com.hrmanagement.service.MonthsService;
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
//@RequestMapping("/months")
//public class MonthsController {
//
//    @Autowired
//    private MonthsService monthsService;
//
//    @GetMapping
//    public ResponseEntity<List<Months>> getAllMonths() {
//        List<Months> monthsList = monthsService.getAllMonths();
//        return ResponseEntity.ok(monthsList);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Months> getMonthById(@PathVariable Integer id) {
//        Months month = monthsService.getMonthById(id);
//        if (month != null) {
//            return ResponseEntity.ok(month);
//        } else {
//            // You can customize the response for not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
