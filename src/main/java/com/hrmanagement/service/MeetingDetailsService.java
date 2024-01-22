package com.hrmanagement.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrmanagement.entities.MeetingDetails;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.repositories.MeetingDetailsRepository;

@Service
public class MeetingDetailsService {

	@Autowired
	private MeetingDetailsRepository meetingDetailsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public MeetingDetails createMeetingDetails(MeetingDetails meetingDetails, Integer clientId) {
        meetingDetails.setClientId(clientId);
        return meetingDetailsRepository.save(meetingDetails);
    }
    public Page<MeetingDetails> getAllMeetingDetailsByClientId(Integer clientId, Pageable pageable) {
        return meetingDetailsRepository.findAllByClientId(clientId, pageable);
    }

    public Optional<MeetingDetails> getMeetingDetailsByMtgIdAndClientId(Integer mtgId, Integer clientId) {
        return meetingDetailsRepository.findByMtgIdAndClientId(mtgId, clientId);
    }
    public MeetingDetails updateMeetingDetails(Integer mtgId, MeetingDetails meetingDetails, Integer clientId) {
        Optional<MeetingDetails> existingMeetingDetails = meetingDetailsRepository.findByMtgIdAndClientId(mtgId, clientId);
        if (existingMeetingDetails.isPresent()) {
        	MeetingDetails updatedMeetingDetails = existingMeetingDetails.get();

            // Use ModelMapper to update fields
            modelMapper.map(meetingDetails, updatedMeetingDetails);

            return meetingDetailsRepository.save(updatedMeetingDetails);
        } else {
            // Throw NotFoundException when leave details with the given ID and client ID are not found
            throw new NotFoundException("MeetingDetails not found with id " + mtgId + " for clientId " + clientId);
        }
    }
    

    @Transactional
    public void deleteMeetingDetailsByMtgIdAndClientId(Integer mtgId, Integer clientId) {
    	meetingDetailsRepository.deleteByMtgIdAndClientId(mtgId, clientId);
    }
    public Page<MeetingDetails> searchDetails(String query, Pageable pageable) {
    	return meetingDetailsRepository.searchDetails(query, pageable);
         
    }
}
