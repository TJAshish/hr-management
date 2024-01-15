package com.hrmanagement.repositories;

import com.hrmanagement.entities.HolidayDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface HolidayDetailsRepository extends JpaRepository<HolidayDetails, Integer> {

//    List<HolidayDetails> findAllByClientId(Integer clientId);
    Page<HolidayDetails> findAllByClientId(Integer clientId, Pageable pageable);
    Optional<HolidayDetails> findByIdAndClientId(Integer id, Integer clientId);

    void deleteByIdAndClientId(Integer id, Integer clientId);
    @Query(value = "SELECT * FROM holidayDetails h WHERE " +
            "LOWER(h.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(h.note) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
List<HolidayDetails> searchDetails(String query);

}

