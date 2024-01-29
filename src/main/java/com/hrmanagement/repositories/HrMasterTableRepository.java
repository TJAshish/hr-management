package com.hrmanagement.repositories;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hrmanagement.entities.HrMasterTable;
@EnableJpaRepositories
public interface HrMasterTableRepository extends JpaRepository<HrMasterTable, Integer>{
	List<HrMasterTable> findByDepartmentNameIsNotNull();
	List<HrMasterTable> findBySalaryRuleIsNotNull();
	List<HrMasterTable> findBySalaryModeIsNotNull();
	List<HrMasterTable> findByTypeIsNotNull();
	List<HrMasterTable> findByFinancialYearIsNotNull();
	List<HrMasterTable> findByMonthIsNotNull();
}
