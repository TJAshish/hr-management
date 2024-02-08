
package com.hrmanagement.entities;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.hrmanagement.dtos.LeaveRequest;

import lombok.Data;

@Data
@Entity
public class ManageLeave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer clientId;
	private String catagory;
	private String type;
	private String designation;
	private String name;
	private String reason;
	private Date fromDate;
	private Date toDate;
	
	private String reportTo;
	private String status;
	private Double avail;
	
	private LocalDateTime applyOn;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "manageLeave", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
//	private List<Comment> comments;


	
	@PrePersist
    protected void onCreate() {
		applyOn = LocalDateTime.now();
		calculateAndSetLeaveDays();
    }
	

    private void calculateAndSetLeaveDays() {
        if (fromDate != null && toDate != null && type != null) {
            // Convert toDate and fromDate to the required format (e.g., "yyyy-MM-dd")
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fromDateStr = dateFormat.format(fromDate);
            String toDateStr = dateFormat.format(toDate);

            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setFromDate(fromDateStr);
            leaveRequest.setToDate(toDateStr);
            leaveRequest.setType(type);

            try {
                avail = calculateLeaveDays(leaveRequest);
            } catch (ParseException e) {
                // Handle exception (e.g., log it or set a default value)
                e.printStackTrace();
                avail = 0.0; // Set a default value or handle it accordingly
            }
        }
    }

    private double calculateLeaveDays(LeaveRequest leaveRequest) throws ParseException {
        Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(leaveRequest.getFromDate());
        Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(leaveRequest.getToDate());
        String type = leaveRequest.getType().toLowerCase();

        // fromdate = todate
        if (fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .isEqual(toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
            return type.equals("half day") ? 0.5 : 1;
        }

        // calculating the difference in days
        long timeDiff = toDate.getTime() - fromDate.getTime();
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        // based on leave type adjust leave
        double adjustedDays = daysDiff + 1;

        if (type.equals("half day")) {
            adjustedDays -= 0.5;
        }

        return adjustedDays;
    }
    public void recalculateAvail() {
        calculateAndSetLeaveDays();
    }
}