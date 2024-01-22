package com.hrmanagement.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class MeetingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mtgId;
	
	private Integer clientId;
	
	private String department;
	
	private String userName;
	
	@Temporal(TemporalType.DATE)
	private Date meetingDate;
	
	private String subject;
	
	private String details;

}
