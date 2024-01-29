package com.hrmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class HrMasterTable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String departmentName;
    private String salaryRule;
    private String salaryMode;
    private String type;
    private String financialYear;
    private String month;

}
