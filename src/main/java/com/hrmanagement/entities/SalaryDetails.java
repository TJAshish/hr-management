package com.hrmanagement.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class SalaryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = Years.class)
    private Years financialYear;

    @OneToOne(targetEntity = Months.class)
    private Months month;
    
    @OneToOne(targetEntity = Department.class)
    private Department department;

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

    @OneToOne(targetEntity = SalaryMode.class)
    private SalaryMode salaryMode;

    private String bankAccountChequeNo;
    
    private String bank;
}
