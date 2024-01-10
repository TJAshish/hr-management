package com.hrmanagement.service;

import com.hrmanagement.entities.HolidayDetails;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.repositories.HolidayDetailsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HolidayDetailsService {

    @Autowired
    private HolidayDetailsRepository holidayDetailsRepository;
    
    @Autowired
	private ModelMapper modelMapper;

    public HolidayDetails createHolidayDetails(Integer clientId, HolidayDetails holidayDetails) {
        holidayDetails.setClientId(clientId);
        return holidayDetailsRepository.save(holidayDetails);
    }

    public Page<HolidayDetails> getAllHolidayDetailsByClientId(Integer clientId, Pageable pageable) {
        return holidayDetailsRepository.findAllByClientId(clientId, pageable);
    }

    public HolidayDetails getHolidayDetailsByIdAndClientId(Integer id, Integer clientId) {
        return holidayDetailsRepository.findByIdAndClientId(id, clientId).orElseThrow(() -> new NotFoundException("HolidayDetails not found for Id: " + id + " and clientId: " + clientId));
    }

    public HolidayDetails updateHolidayDetails(Integer clientId, Integer id, HolidayDetails updatedHolidayDetails) {
        Optional<HolidayDetails> optionalHolidayDetails = holidayDetailsRepository.findByIdAndClientId(id, clientId);
        if (optionalHolidayDetails.isPresent()) {
            HolidayDetails holidayDetails = optionalHolidayDetails.get();

            // Use ModelMapper to update fields
            modelMapper.map(updatedHolidayDetails, holidayDetails);

            return holidayDetailsRepository.save(holidayDetails);
        } else {
            throw new NotFoundException("HolidayDetails not found");
        }
    }

    public void deleteHolidayDetails(Integer clientId, Integer id) {
        Optional<HolidayDetails> optionalHolidayDetails = holidayDetailsRepository.findByIdAndClientId(id, clientId);
        if (optionalHolidayDetails.isPresent()) {
            holidayDetailsRepository.deleteByIdAndClientId(id, clientId);
        } else {
            throw new NotFoundException("HolidayDetails not found");
        }
    }
}
