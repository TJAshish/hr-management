package com.hrmanagement.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrmanagement.entities.ManageLeave;
import com.hrmanagement.exception.NotFoundException;
import com.hrmanagement.repositories.ManageLeaveRepository;

@Service
public class ManageLeaveService {
	
	@Autowired
	private ManageLeaveRepository manageLeaveRepository;
	
	@Autowired
	private ModelMapper modelMapper;


	public ManageLeave createManageLeave(ManageLeave manageLeave, Integer clientId) {
		manageLeave.setClientId(clientId);
        return manageLeaveRepository.save(manageLeave);
    }
	
    public Page<ManageLeave> getAllManageLeaveByClientId(Integer clientId, Pageable pageable) {
        return manageLeaveRepository.findAllByClientId(clientId, pageable);
    }

    public Optional<ManageLeave> getManageLeaveByIdAndClientId(Integer id, Integer clientId) {
        return manageLeaveRepository.findByIdAndClientId(id, clientId);
    }

    public ManageLeave updateManageLeave(Integer id, ManageLeave manageLeave, Integer clientId) {
        Optional<ManageLeave> existingManageLeave = manageLeaveRepository.findByIdAndClientId(id, clientId);
        if (existingManageLeave.isPresent()) {
        	ManageLeave updatedManageLeave = existingManageLeave.get();

            // Use ModelMapper to update fields
            modelMapper.map(manageLeave, updatedManageLeave);

            return manageLeaveRepository.save(updatedManageLeave);
        } else {
            // Throw NotFoundException when leave details with the given ID and client ID are not found
            throw new NotFoundException("ManageLeave not found with id " + id + " for clientId " + clientId);
        }
    }
    

    @Transactional
    public void deleteManageLeaveByIdAndClientId(Integer id, Integer clientId) {
    	manageLeaveRepository.deleteByIdAndClientId(id, clientId);
    }
    public Page<ManageLeave> searchDetails(String query, Pageable pageable) {
    	return manageLeaveRepository.searchDetails(query, pageable);
         
    }
}
