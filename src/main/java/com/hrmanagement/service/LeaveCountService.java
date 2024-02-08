package com.hrmanagement.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrmanagement.entities.LeaveCount;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.repositories.LeaveCountRepository;
import com.hrmanagement.repositories.LeaveDetailsRepository;

@Service
public class LeaveCountService {

	@Autowired
    private LeaveCountRepository leaveCountRepository;
	@Autowired
	private ModelMapper modelMapper;


	public LeaveCount createLeaveCount(LeaveCount leaveCount, Integer clientId) {
        leaveCount.setClientId(clientId);
        return leaveCountRepository.save(leaveCount);
    }
	
    public Page<LeaveCount> getAllLeaveCountByClientId(Integer clientId, Pageable pageable) {
        return leaveCountRepository.findAllByClientId(clientId, pageable);
    }
    public List<LeaveCount> getAllLeaveCountByClientId(Integer clientId) {
        return leaveCountRepository.findAllByClientId(clientId);
    }

    public Optional<LeaveCount> getLeaveCountByIdAndClientId(Integer id, Integer clientId) {
        return leaveCountRepository.findByIdAndClientId(id, clientId);
    }

    public LeaveCount updateLeaveCount(Integer id, LeaveCount leaveCount, Integer clientId) {
        Optional<LeaveCount> existingLeaveCount = leaveCountRepository.findByIdAndClientId(id, clientId);
        if (existingLeaveCount.isPresent()) {
            LeaveCount updatedLeaveCount = existingLeaveCount.get();

            // Use ModelMapper to update fields
            modelMapper.map(leaveCount, updatedLeaveCount);

            return leaveCountRepository.save(updatedLeaveCount);
        } else {
            // Throw NotFoundException when leave Count with the given ID and client ID are not found
            throw new NotFoundException("LeaveCount not found with id " + id + " for clientId " + clientId);
        }
    }
    

    @Transactional
    public void deleteLeaveCountByIdAndClientId(Integer id, Integer clientId) {
        leaveCountRepository.deleteByIdAndClientId(id, clientId);
    }
    public Page<LeaveCount> searchDetails(String query, Pageable pageable) {
    	return leaveCountRepository.searchDetails(query, pageable);
         
    }
}
