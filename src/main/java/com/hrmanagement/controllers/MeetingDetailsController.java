package com.hrmanagement.controllers;

import com.hrmanagement.entities.MeetingDetails;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.service.MeetingDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting-details")
public class MeetingDetailsController {

    @Autowired
    private MeetingDetailsService meetingDetailsService;

    @PostMapping("/{clientId}")
    public ResponseEntity<MeetingDetails> createMeetingDetails(@PathVariable Integer clientId, @RequestBody MeetingDetails meetingDetails) {
        MeetingDetails createdMeetingDetails = meetingDetailsService.createMeetingDetails(meetingDetails, clientId);
        return new ResponseEntity<>(createdMeetingDetails, HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Page<MeetingDetails>> getAllMeetingDetailsByClientId(@PathVariable("clientId") Integer clientId,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MeetingDetails> meetingDetailsPage = meetingDetailsService.getAllMeetingDetailsByClientId(clientId, pageable);
        return new ResponseEntity<>(meetingDetailsPage, HttpStatus.OK);
    }

    @GetMapping("/{mtgId}/{clientId}")
    public ResponseEntity<MeetingDetails> getMeetingDetailsByMtgIdAndClientId(@PathVariable Integer clientId, @PathVariable Integer mtgId) {
        MeetingDetails meetingDetails = meetingDetailsService.getMeetingDetailsByMtgIdAndClientId(mtgId, clientId)
                .orElseThrow(() -> new NotFoundException("MeetingDetails not found with id " + mtgId + " for clientId " + clientId));
        return new ResponseEntity<>(meetingDetails, HttpStatus.OK);
    }

    @PutMapping("/{mtgId}/{clientId}")
    public ResponseEntity<MeetingDetails> updateMeetingDetails(@PathVariable Integer clientId, @PathVariable Integer mtgId, @RequestBody MeetingDetails updatedMeetingDetails) {
        MeetingDetails updatedDetails = meetingDetailsService.updateMeetingDetails(mtgId, updatedMeetingDetails, clientId);
        return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{mtgId}/{clientId}")
    public ResponseEntity<Void> deleteMeetingDetails(@PathVariable Integer clientId, @PathVariable Integer mtgId) {
        meetingDetailsService.deleteMeetingDetailsByMtgIdAndClientId(mtgId, clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MeetingDetails>> searchDetails(@RequestParam("query") String query,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(meetingDetailsService.searchDetails(query, pageable));
    }
}
