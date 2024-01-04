package com.hrmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrmanagement.entities.SalaryDetails;

public interface SalaryDetailsRepository extends JpaRepository<SalaryDetails , Integer> {

}
