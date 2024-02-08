package com.hrmanagement.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.hrmanagement.dtos.Department;
import com.hrmanagement.dtos.SalaryMode;
import com.hrmanagement.dtos.SalaryRule;

import lombok.Data;

@Entity
@Data
public class Employees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	
	private Integer clientId;
	
	@Transient
//	@OneToOne(targetEntity = Department.class)
	@ManyToOne(fetch = FetchType.EAGER)
	private Department department;
	
	private String departmentName;
	
	private Integer code;
	private String userName;
	private String password;
	private Long adharNo;
	private String fullName;
	private String fatherName;
	private String subject;
	@Temporal(TemporalType.DATE)
	private Date dob;
	@Temporal(TemporalType.DATE)
	private Date joiningDate;
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
	@Transient
	@ManyToOne(fetch = FetchType.EAGER)
	private SalaryMode salaryModeId;
	
	private String salaryMode;
	
	private String bankName;
	private String accNo;
	private String remark;
	
	@Transient
	@ManyToOne(fetch = FetchType.EAGER)
	private SalaryRule salaryRuleId;
	
	private String salaryRule;
	
//	@OneToMany(mappedBy = "empId")
//	private List<Comment> comments;
}
