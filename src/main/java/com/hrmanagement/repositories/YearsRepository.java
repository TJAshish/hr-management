package com.hrmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hrmanagement.entities.Years;
@EnableJpaRepositories
public interface YearsRepository extends JpaRepository<Years, Integer> {
}