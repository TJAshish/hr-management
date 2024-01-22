package com.hrmanagement.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.hrmanagement.entities.FeedbackDetails;

@EnableJpaRepositories
public interface FeedbackDetailsRepository extends JpaRepository<FeedbackDetails, Integer> {
    Optional<FeedbackDetails> findByClientIdAndId(Integer clientId, Integer id);
    Page<FeedbackDetails> findByClientId(Integer clientId, Pageable pageable);
    @Query(value = "SELECT * FROM feedbackDetails f WHERE " +
            "LOWER(f.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(f.studentDetails) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(f.feedback) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
Page<FeedbackDetails> searchDetails(String query ,Pageable pageable);

}

