package com.hrmanagement.dtos;

import lombok.Data;

@Data
public class LeaveRequest {
	private String fromDate;
    private String toDate;
    private String type;
}
