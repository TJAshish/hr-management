package com.hrmanagement.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hrmanagement.entities.ManageLeave;

@EnableJpaRepositories
public interface ManageLeaveRepository extends JpaRepository<ManageLeave, Integer>{
	Page<ManageLeave> findAllByClientId(Integer clientId, Pageable pageable);
    Optional<ManageLeave> findByIdAndClientId(Integer id, Integer clientId);

    void deleteByIdAndClientId(Integer id, Integer clientId);
    @Query(value = "SELECT * FROM  manage_leave l WHERE " +
            "LOWER(l.catagery) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(l.name) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
Page<ManageLeave> searchDetails(String query ,Pageable pageable);

}
