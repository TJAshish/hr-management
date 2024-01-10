package com.hrmanagement.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class HolidayDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private Integer clientId;
	private String title;
	@Temporal(TemporalType.DATE)
    private Date fromDate;
	@Temporal(TemporalType.DATE)
    private Date toDate;
    private String note;

}
