package com.hrmanagement.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.hrmanagement.dtos.Department;
import com.hrmanagement.dtos.Months;
import com.hrmanagement.dtos.SalaryMode;
import com.hrmanagement.dtos.Years;

import lombok.Data;

@Data
@Entity

public class SalaryDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sdId; 
	
    private Integer clientId;
    

    @Transient
    @ManyToOne(targetEntity = Years.class)
    private Years financialYear;
    private String year;

    @Transient
    @ManyToOne(targetEntity = Months.class)
    private Months months;
    private String month;
    
    
    @Transient
	@OneToOne(targetEntity = Department.class)
	private Department department;
	
	private String departmentName;
	
    private String name;

    private String userCode;

    private String fathersName;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    private Double basic;

    private Double hra;

    private Double petrolConveyance;

    private Double incentive;

    private Double others;

    private Double elOpen;

    private Double elAvi;

    private Double slOpen;

    private Double slAvi;

    private Double clOpen;

    private Double clAvi;

    private Double elBal;

    private Double slBal;

    private Double clBal;

    private Integer monthDays;

    private Integer workingDays;

    private Integer late;

    private Double grossSalary;

    private Double perDaySalary;

    private Double pf;

    private Double esi;

    private Double exGratia;

    private Double advStafft;

    private Double othersLate;

    private Double deduction;

    private Double netPay;
    @Transient
	@OneToOne(targetEntity = SalaryMode.class)
	private SalaryMode salaryModeId;
	
	private String salaryMode;

    private String bankAccountChequeNo;
    
    private String bank;
}
