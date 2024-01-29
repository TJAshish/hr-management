package com.hrmanagement.controllers;

import com.hrmanagement.dtos.DayType;
import com.hrmanagement.dtos.Department;
import com.hrmanagement.dtos.Months;
import com.hrmanagement.dtos.SalaryMode;
import com.hrmanagement.dtos.SalaryRule;
import com.hrmanagement.dtos.Years;
import com.hrmanagement.service.HrMasterTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class HrMasterTableController {

    @Autowired
    private HrMasterTableService hrMasterTableService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return hrMasterTableService.getAllDepartment();
    }

    @GetMapping("/years")
    public List<Years> getAllYears() {
        return hrMasterTableService.getAllYears();
    }

    @GetMapping("/months")
    public List<Months> getAllMonths() {
        return hrMasterTableService.getAllMonths();
    }

    @GetMapping("/salary-modes")
    public List<SalaryMode> getAllSalaryModes() {
        return hrMasterTableService.getAllSalaryMode();
    }

    @GetMapping("/salary-rules")
    public List<SalaryRule> getAllSalaryRules() {
        return hrMasterTableService.getAllSalaryRule();
    }
    @GetMapping("/day-type")
    public List<DayType> getAllDayType() {
        return hrMasterTableService.getAllDayType();
    }
}
