package com.hrmanagement.controllers;

import com.hrmanagement.entities.HolidayDetails;
import com.hrmanagement.service.HolidayDetailsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/holiday-details")
public class HolidayDetailsController {

	@Autowired
    private HolidayDetailsService holidayDetailsService;


    @PostMapping("/{clientId}")
    public ResponseEntity<HolidayDetails> createHolidayDetails(@PathVariable Integer clientId, @RequestBody HolidayDetails holidayDetails) {
        HolidayDetails createdHolidayDetails = holidayDetailsService.createHolidayDetails(clientId, holidayDetails);
        return new ResponseEntity<>(createdHolidayDetails, HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Page<HolidayDetails>> getAllHolidayDetailsByClientId(@PathVariable("clientId") Integer clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<HolidayDetails> holidayDetailsPage = holidayDetailsService.getAllHolidayDetailsByClientId(clientId, pageable);
        return new ResponseEntity<>(holidayDetailsPage, HttpStatus.OK);
    }

    @GetMapping("/{id}/{clientId}")
    public ResponseEntity<HolidayDetails> getHolidayDetailsByIdAndClientId(@PathVariable Integer clientId, @PathVariable Integer id) {
        HolidayDetails holidayDetails = holidayDetailsService.getHolidayDetailsByIdAndClientId(id, clientId);
        return new ResponseEntity<>(holidayDetails, HttpStatus.OK);
    }

    @PutMapping("/{id}/{clientId}")
    public ResponseEntity<HolidayDetails> updateHolidayDetails(@PathVariable Integer clientId, @PathVariable Integer id, @RequestBody HolidayDetails updatedHolidayDetails) {
        HolidayDetails updatedDetails = holidayDetailsService.updateHolidayDetails(clientId, id, updatedHolidayDetails);
        return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{clientId}")
    public ResponseEntity<Void> deleteHolidayDetails(@PathVariable Integer clientId, @PathVariable Integer id) {
        holidayDetailsService.deleteHolidayDetails(clientId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<HolidayDetails>> searchDetais(@RequestParam("query") String query){
        return ResponseEntity.ok(holidayDetailsService.searchDetails(query));
    }
}
