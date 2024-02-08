package com.hrmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrmanagement.entities.LeaveCount;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.service.LeaveCountService;

@RestController
@RequestMapping("/leave-count")
public class LeaveCountController {
	@Autowired
    private LeaveCountService leaveCountService;

    @PostMapping("/{clientId}")
    public ResponseEntity<LeaveCount> createLeaveCount(@PathVariable Integer clientId, @RequestBody LeaveCount leaveCount) {
        LeaveCount createdLeaveCount = leaveCountService.createLeaveCount(leaveCount, clientId);
        return new ResponseEntity<>(createdLeaveCount, HttpStatus.CREATED);
    }

    @GetMapping("/all/{clientId}")
    public List<LeaveCount> getAllLeaveCountByClientId(@PathVariable("clientId") Integer clientId) {
        return leaveCountService.getAllLeaveCountByClientId(clientId);
    }
    @GetMapping("/{clientId}")
    public ResponseEntity<Page<LeaveCount>> getAllLeaveCountByClientId(@PathVariable("clientId") Integer clientId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LeaveCount> leaveCountPage = leaveCountService.getAllLeaveCountByClientId(clientId, pageable);
        return new ResponseEntity<>(leaveCountPage, HttpStatus.OK);
    }

    @GetMapping("/{id}/{clientId}")
    public ResponseEntity<LeaveCount> getLeaveCountByIdAndClientId(@PathVariable Integer clientId, @PathVariable Integer id) {
        LeaveCount leaveCount = leaveCountService.getLeaveCountByIdAndClientId(id, clientId)
                .orElseThrow(() -> new NotFoundException("LeaveCount not found with id " + id + " for clientId " + clientId));
        return new ResponseEntity<>(leaveCount, HttpStatus.OK);
    }

    @PutMapping("/{id}/{clientId}")
    public ResponseEntity<LeaveCount> updateLeaveCount(@PathVariable Integer clientId, @PathVariable Integer id, @RequestBody LeaveCount updatedLeaveCount) {
        LeaveCount updatedCount = leaveCountService.updateLeaveCount(id, updatedLeaveCount, clientId);
        return new ResponseEntity<>(updatedCount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{clientId}")
    public ResponseEntity<Void> deleteLeaveDetails(@PathVariable Integer clientId, @PathVariable Integer id) {
        leaveCountService.deleteLeaveCountByIdAndClientId(id, clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<LeaveCount>> searchDetais(@RequestParam("query") String query,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(leaveCountService.searchDetails(query,pageable));
    }
}
