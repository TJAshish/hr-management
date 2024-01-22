package com.hrmanagement.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hrmanagement.entities.MeetingDetails;

@EnableJpaRepositories
public interface MeetingDetailsRepository extends JpaRepository<MeetingDetails,Integer> {
    Page<MeetingDetails> findAllByClientId(Integer clientId, Pageable pageable);
    Optional<MeetingDetails> findByMtgIdAndClientId(Integer mtgId, Integer clientId);

    void deleteByMtgIdAndClientId(Integer mtgId, Integer clientId);
    @Query(value = "SELECT * FROM meeting_details m WHERE " +
            "LOWER(m.subject) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(m.department) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(m.user_name) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
Page<MeetingDetails> searchDetails(String query ,Pageable pageable);
}
