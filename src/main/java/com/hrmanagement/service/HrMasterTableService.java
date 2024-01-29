package com.hrmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrmanagement.dtos.DayType;
import com.hrmanagement.dtos.Department;
import com.hrmanagement.dtos.Months;
import com.hrmanagement.dtos.SalaryMode;
import com.hrmanagement.dtos.SalaryRule;
import com.hrmanagement.dtos.Years;
import com.hrmanagement.entities.HrMasterTable;
import com.hrmanagement.repositories.HrMasterTableRepository;

@Service
public class HrMasterTableService {

	@Autowired
	private HrMasterTableRepository hrMasterTableRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	 public List<Department> getAllDepartment() {
	        List<HrMasterTable> hrMasterTables = hrMasterTableRepository.findByDepartmentNameIsNotNull();
	        return hrMasterTables.stream()
	                .map(this::convertToDpt)
	                .collect(Collectors.toList());
	    }
	 public List<Years> getAllYears() {
	        List<HrMasterTable> hrMasterTables = hrMasterTableRepository.findByFinancialYearIsNotNull();
	        return hrMasterTables.stream()
	                .map(this::convertToYear)
	                .collect(Collectors.toList());
	    }
	 public List<Months> getAllMonths() {
	        List<HrMasterTable> hrMasterTables = hrMasterTableRepository.findByMonthIsNotNull();
	        return hrMasterTables.stream()
	                .map(this::convertToMon)
	                .collect(Collectors.toList());
	    }
	 public List<SalaryMode> getAllSalaryMode() {
	        List<HrMasterTable> hrMasterTables = hrMasterTableRepository.findBySalaryModeIsNotNull();
	        return hrMasterTables.stream()
	                .map(this::convertToSm)
	                .collect(Collectors.toList());
	    }
	 public List<SalaryRule> getAllSalaryRule() {
	        List<HrMasterTable> hrMasterTables = hrMasterTableRepository.findBySalaryRuleIsNotNull();
	        return hrMasterTables.stream()
	                .map(this::convertToSr)
	                .collect(Collectors.toList());
	    }
	 public List<DayType> getAllDayType() {
	        List<HrMasterTable> hrMasterTables = hrMasterTableRepository.findByTypeIsNotNull();
	        return hrMasterTables.stream()
	                .map(this::convertToDt)
	                .collect(Collectors.toList());
	    }

	    private Department convertToDpt(HrMasterTable hrMasterTable) {
	        return modelMapper.map(hrMasterTable, Department.class);
	    }
	    private Years convertToYear(HrMasterTable hrMasterTable) {
	        return modelMapper.map(hrMasterTable, Years.class);
	    }
	    private Months convertToMon(HrMasterTable hrMasterTable) {
	        return modelMapper.map(hrMasterTable, Months.class);
	    }
	    private SalaryRule convertToSr(HrMasterTable hrMasterTable) {
	        return modelMapper.map(hrMasterTable, SalaryRule.class);
	    }
	    private SalaryMode convertToSm(HrMasterTable hrMasterTable) {
	        return modelMapper.map(hrMasterTable, SalaryMode.class);
	    }
	    private DayType convertToDt(HrMasterTable hrMasterTable) {
	        return modelMapper.map(hrMasterTable, DayType.class);
	    }
	    
}
