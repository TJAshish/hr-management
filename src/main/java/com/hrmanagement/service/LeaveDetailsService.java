package com.hrmanagement.service;

import com.hrmanagement.entities.LeaveDetails;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.repositories.LeaveDetailsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveDetailsService {

	@Autowired
    private LeaveDetailsRepository leaveDetailsRepository;
	@Autowired
	private ModelMapper modelMapper;


	public LeaveDetails createLeaveDetails(LeaveDetails leaveDetails, Integer clientId) {
        leaveDetails.setClientId(clientId);
        return leaveDetailsRepository.save(leaveDetails);
    }
	
    public Page<LeaveDetails> getAllLeaveDetailsByClientId(Integer clientId, Pageable pageable) {
        return leaveDetailsRepository.findAllByClientId(clientId, pageable);
    }

    public Optional<LeaveDetails> getLeaveDetailsByIdAndClientId(Integer id, Integer clientId) {
        return leaveDetailsRepository.findByIdAndClientId(id, clientId);
    }

    public LeaveDetails updateLeaveDetails(Integer id, LeaveDetails leaveDetails, Integer clientId) {
        Optional<LeaveDetails> existingLeaveDetails = leaveDetailsRepository.findByIdAndClientId(id, clientId);
        if (existingLeaveDetails.isPresent()) {
            LeaveDetails updatedLeaveDetails = existingLeaveDetails.get();

            // Use ModelMapper to update fields
            modelMapper.map(leaveDetails, updatedLeaveDetails);

            return leaveDetailsRepository.save(updatedLeaveDetails);
        } else {
            // Throw NotFoundException when leave details with the given ID and client ID are not found
            throw new NotFoundException("LeaveDetails not found with id " + id + " for clientId " + clientId);
        }
    }

    public void deleteLeaveDetailsByIdAndClientId(Integer id, Integer clientId) {
        leaveDetailsRepository.deleteByIdAndClientId(id, clientId);
    }
    public List<LeaveDetails> searchDetails(String query) {
        List<LeaveDetails> leaveDetails = leaveDetailsRepository.searchDetails(query);
        return leaveDetails;
    }

}
