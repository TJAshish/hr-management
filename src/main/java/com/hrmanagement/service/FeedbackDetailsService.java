package com.hrmanagement.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hrmanagement.entities.FeedbackDetails;
import com.hrmanagement.repositories.FeedbackDetailsRepository;

@Service
public class FeedbackDetailsService {

    private final FeedbackDetailsRepository feedbackDetailsRepository;

    public FeedbackDetailsService(FeedbackDetailsRepository feedbackDetailsRepository) {
        this.feedbackDetailsRepository = feedbackDetailsRepository;
    }

    public Optional<FeedbackDetails> getFeedbackDetailsByClientIdAndId(Integer clientId, Integer id) {
        return feedbackDetailsRepository.findByClientIdAndId(clientId, id);
    }

    public Page<FeedbackDetails> getAllFeedbackDetailsByClientId(Integer clientId, Pageable pageable) {
        return feedbackDetailsRepository.findByClientId(clientId, pageable);
    }
}
