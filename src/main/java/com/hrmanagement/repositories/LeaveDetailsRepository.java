package com.hrmanagement.repositories;


import com.hrmanagement.entities.LeaveDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveDetailsRepository extends JpaRepository<LeaveDetails, Integer> {

    Page<LeaveDetails> findAllByClientId(Integer clientId, Pageable pageable);
    Optional<LeaveDetails> findByIdAndClientId(Integer id, Integer clientId);

    void deleteByIdAndClientId(Integer id, Integer clientId);
}

