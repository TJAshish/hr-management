package com.hrmanagement.repositories;

import com.hrmanagement.entities.SalaryDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface SalaryDetailsRepository extends JpaRepository<SalaryDetails, Integer> {

    Optional<SalaryDetails> findBySdIdAndClientId(Integer sdId, Integer clientId);

    Page<SalaryDetails> findByClientId(Integer clientId, Pageable pageable);

    List<SalaryDetails> findByNameContainingAndClientId(String keyword, Integer clientId);

    boolean existsBySdIdAndClientId(Integer sdId, Integer clientId);

    void deleteBySdIdAndClientId(Integer sdId, Integer clientId);
    @Query(value = "SELECT * FROM salaryDetails s WHERE " +
            "LOWER(s.year) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.month) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.department) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
List<SalaryDetails> searchDetails(String query);

}
