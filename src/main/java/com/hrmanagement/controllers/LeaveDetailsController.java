package com.hrmanagement.controllers;

import com.hrmanagement.entities.LeaveDetails;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.service.LeaveDetailsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave-details")
public class LeaveDetailsController {

    @Autowired
    private LeaveDetailsService leaveDetailsService;

    @PostMapping("/{clientId}")
    public ResponseEntity<LeaveDetails> createLeaveDetails(@PathVariable Integer clientId, @RequestBody LeaveDetails leaveDetails) {
        LeaveDetails createdLeaveDetails = leaveDetailsService.createLeaveDetails(leaveDetails, clientId);
        return new ResponseEntity<>(createdLeaveDetails, HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Page<LeaveDetails>> getAllLeaveDetailsByClientId(@PathVariable("clientId") Integer clientId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LeaveDetails> leaveDetailsPage = leaveDetailsService.getAllLeaveDetailsByClientId(clientId, pageable);
        return new ResponseEntity<>(leaveDetailsPage, HttpStatus.OK);
    }

    @GetMapping("/{id}/{clientId}")
    public ResponseEntity<LeaveDetails> getLeaveDetailsByIdAndClientId(@PathVariable Integer clientId, @PathVariable Integer id) {
        LeaveDetails leaveDetails = leaveDetailsService.getLeaveDetailsByIdAndClientId(id, clientId)
                .orElseThrow(() -> new NotFoundException("LeaveDetails not found with id " + id + " for clientId " + clientId));
        return new ResponseEntity<>(leaveDetails, HttpStatus.OK);
    }

    @PutMapping("/{id}/{clientId}")
    public ResponseEntity<LeaveDetails> updateLeaveDetails(@PathVariable Integer clientId, @PathVariable Integer id, @RequestBody LeaveDetails updatedLeaveDetails) {
        LeaveDetails updatedDetails = leaveDetailsService.updateLeaveDetails(id, updatedLeaveDetails, clientId);
        return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{clientId}")
    public ResponseEntity<Void> deleteLeaveDetails(@PathVariable Integer clientId, @PathVariable Integer id) {
        leaveDetailsService.deleteLeaveDetailsByIdAndClientId(id, clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/search")
    public ResponseEntity<List<LeaveDetails>> searchDetais(@RequestParam("query") String query){
        return ResponseEntity.ok(leaveDetailsService.searchDetails(query));
    }
}
