package com.hrmanagement.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hrmanagement.entities.FeedbackDetails;

@EnableJpaRepositories
public interface FeedbackDetailsRepository extends JpaRepository<FeedbackDetails, Integer> {
    Optional<FeedbackDetails> findByClientIdAndId(Integer clientId, Integer id);
    Page<FeedbackDetails> findByClientId(Integer clientId, Pageable pageable);
}

