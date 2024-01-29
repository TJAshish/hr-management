package com.hrmanagement.repositories;

import com.hrmanagement.entities.StudentDoc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface StudentDocRepository extends JpaRepository<StudentDoc, Integer> {
	Page<StudentDoc> findByClientId(Integer clientId, Pageable pageable);
	 StudentDoc findByIdAndClientId(Integer id, Integer clientId);
	 void deleteByIdAndClientId(Integer id, Integer clientId);
	 @Query(value = "SELECT * FROM student_doc s WHERE " +
	            "LOWER(s.enrollment_id) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
	Page<StudentDoc> searchDetails(String query ,Pageable pageable);
}
