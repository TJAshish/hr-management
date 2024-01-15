package com.hrmanagement.repositories;


import com.hrmanagement.entities.LeaveDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LeaveDetailsRepository extends JpaRepository<LeaveDetails, Integer> {

    Page<LeaveDetails> findAllByClientId(Integer clientId, Pageable pageable);
    Optional<LeaveDetails> findByIdAndClientId(Integer id, Integer clientId);

    void deleteByIdAndClientId(Integer id, Integer clientId);
    @Query(value = "SELECT * FROM leaveDetails l WHERE " +
            "LOWER(l.leaveName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(l.description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
List<LeaveDetails> searchDetails(String query);

}

