package com.hrmanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hrmanagement.entities.LeaveCount;

@EnableJpaRepositories
public interface LeaveCountRepository extends JpaRepository<LeaveCount, Integer> {

    Page<LeaveCount> findAllByClientId(Integer clientId, Pageable pageable);
    List<LeaveCount> findAllByClientId(Integer clientId);
    Optional<LeaveCount> findByIdAndClientId(Integer id, Integer clientId);

    void deleteByIdAndClientId(Integer id, Integer clientId);
    @Query(value = "SELECT * FROM leave_count l WHERE " +
            "LOWER(l.count) LIKE LOWER(CONCAT('%', :query, '%')) OR " +

            "LOWER(l.session_year) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(l.category) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
Page<LeaveCount> searchDetails(String query ,Pageable pageable);
}
