package com.hrmanagement.repositories;

import com.hrmanagement.dtos.CommentEmployeeDto;
import com.hrmanagement.entities.Comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findAll(Pageable pageable);
    List<Comment> findByManageLeaveId(Integer manageLeaveId);
}
