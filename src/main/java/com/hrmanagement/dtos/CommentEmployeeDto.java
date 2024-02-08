package com.hrmanagement.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEmployeeDto {
	private Integer commentId;
    private Integer employeeId;
    private String employeeName;
    private String employeePhoto;
    private String content;
    private LocalDateTime commentDate;
    private Integer manageLeaveId;

}
