package com.hrmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrmanagement.entities.FeedbackDetails;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.service.FeedbackDetailsService;

@RestController
@RequestMapping("/feedback-details")
public class FeedbackDetailsController {
	
	@Autowired
	private FeedbackDetailsService feedbackDetailsService;
	
    @GetMapping("/{clientId}")
    public ResponseEntity<Page<FeedbackDetails>> getAllFeedbackDetailsByClientId(@PathVariable("clientId") Integer clientId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FeedbackDetails> feedbackDetailsPage = feedbackDetailsService.getAllFeedbackDetailsByClientId(clientId, pageable);
        return new ResponseEntity<>(feedbackDetailsPage, HttpStatus.OK);
    }

    @GetMapping("/{id}/{clientId}")
    public ResponseEntity<FeedbackDetails> getFeedbackDetailsByIdAndClientId(@PathVariable Integer clientId, @PathVariable Integer id) {
    	FeedbackDetails feedbackDetails = feedbackDetailsService.getFeedbackDetailsByClientIdAndId(clientId, id)
                .orElseThrow(() -> new NotFoundException("FeedbackDetails not found with id " + id + " for clientId " + clientId));
        return new ResponseEntity<>(feedbackDetails, HttpStatus.OK);
    }

}
