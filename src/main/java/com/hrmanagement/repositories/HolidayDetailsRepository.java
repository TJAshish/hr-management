package com.hrmanagement.repositories;

import com.hrmanagement.entities.HolidayDetails; 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface HolidayDetailsRepository extends JpaRepository<HolidayDetails, Integer> {

//    List<HolidayDetails> findAllByClientId(Integer clientId);
    Page<HolidayDetails> findAllByClientId(Integer clientId, Pageable pageable);
    Optional<HolidayDetails> findByIdAndClientId(Integer id, Integer clientId);

    void deleteByIdAndClientId(Integer id, Integer clientId);
    @Query(value = "SELECT * FROM holiday_details h WHERE " +
            "LOWER(h.title) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
Page<HolidayDetails> searchDetails(String query ,Pageable pageable);
}

