package com.hrmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "emp_id")
    private Integer empId;
    

    private String content;

    private LocalDateTime date;
    
    private Integer manageLeaveId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "manage_leave_id")
//    
//    private ManageLeave manageLeave;
   

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
}
