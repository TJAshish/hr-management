package com.hrmanagement.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FILE_STORAGE")
@Getter
@Setter
@NoArgsConstructor
public class FileStorage {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name = "ID", columnDefinition = "char(36)")
	private UUID id;
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(name = "FILE_ID", columnDefinition = "char(36)")
	private UUID fileId;

	private String type;

	private String fileName;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE")
	private Date updateDate;

}

