package com.hrmanagement.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.hrmanagement.entities.LeaveDetails;
import com.hrmanagement.entities.ManageLeave;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.service.ManageLeaveService;

public class ManageLeaveController {
	
	@Autowired
	private ManageLeaveService manageLeaveService;

	@PostMapping("/{clientId}")
    public ResponseEntity<ManageLeave> createLeaveDetails(@PathVariable Integer clientId, @RequestBody ManageLeave manageLeave) {
		ManageLeave createdManageLeave = manageLeaveService.createManageLeave(manageLeave, clientId);
        return new ResponseEntity<>(createdManageLeave, HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Page<ManageLeave>> getAllManageLeaveByClientId(@PathVariable("clientId") Integer clientId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ManageLeave> manageLeavePage = manageLeaveService.getAllManageLeaveByClientId(clientId, pageable);
        return new ResponseEntity<>(manageLeavePage, HttpStatus.OK);
    }

    @GetMapping("/{id}/{clientId}")
    public ResponseEntity<ManageLeave> getLeaveDetailsByIdAndClientId(@PathVariable Integer clientId, @PathVariable Integer id) {
        ManageLeave manageLeave = manageLeaveService.getManageLeaveByIdAndClientId(id, clientId)
                .orElseThrow(() -> new NotFoundException("ManageLeave not found with id " + id + " for clientId " + clientId));
        return new ResponseEntity<>(manageLeave, HttpStatus.OK);
    }

    @PutMapping("/{id}/{clientId}")
    public ResponseEntity<ManageLeave> updateLeaveDetails(@PathVariable Integer clientId, @PathVariable Integer id, @RequestBody ManageLeave updatedManageLeave) {
    	ManageLeave updatedDetails = manageLeaveService.updateManageLeave(id, updatedManageLeave, clientId);
        return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{clientId}")
    public ResponseEntity<Void> deleteManageLeave(@PathVariable Integer clientId, @PathVariable Integer id) {
    	manageLeaveService.deleteManageLeaveByIdAndClientId(id, clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<ManageLeave>> searchDetais(@RequestParam("query") String query,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(manageLeaveService.searchDetails(query,pageable));
    }
}

