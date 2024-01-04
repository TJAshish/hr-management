package com.hrmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Employees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	
	private Integer clientId;
	
	@OneToOne(targetEntity = Department.class)
	private Department departmentId;
	
	private Integer code;
	private String userName;
	private String password;
	private Integer adharNo;
	private String fullName;
	private String fatherName;
	private String subject;
	private String dob;
	private String joiningDate;
	private String education;
	private String email;
	private String currentAddress;
	private String permanentAddress;
	private String mobile;
	private String lastCompany;
	private String contact;
	private String emgContact;
	private String photo;
	private Integer basicSalary;
	private Integer hra;
	private Integer petrolconv;
	private String others;
	private Integer incentive;
	private Integer esic;
	private Integer upa;
	private Integer grossSalary;
	@OneToOne(targetEntity = SalaryMode.class)
	private SalaryMode salaryModeId;
	private String bankName;
	private String accNo;
	private String remark;
	@OneToOne(targetEntity = SalaryRule.class)
	private SalaryRule salaryRuleId;
}
