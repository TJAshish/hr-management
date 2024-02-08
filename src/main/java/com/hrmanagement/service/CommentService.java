package com.hrmanagement.service;

import com.hrmanagement.dtos.CommentEmployeeDto;  
import com.hrmanagement.entities.Comment;
import com.hrmanagement.entities.Employees;
import com.hrmanagement.repositories.CommentRepository;
import com.hrmanagement.repositories.EmployeesRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private EmployeesRepository employeesRepository;
    
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Page<Comment> getAllCommentsWithPagination(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return commentRepository.findAll(pageable);
    }

//    public Comment getCommentById(int commentId) {
//        return commentRepository.findById(commentId).orElse(null);
//    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
    public Comment updateComment(int commentId, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(commentId).orElse(null);

        if (existingComment != null) {
            // Update fields based on your requirements
            existingComment.setContent(updatedComment.getContent());
            // Add more fields as needed

            return commentRepository.save(existingComment);
        }

        return null; // or throw an exception indicating that the comment was not found
    }

    public void deleteComment(int commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CommentEmployeeDto> getAllCommentsByManageLeaveId(Integer manageLeaveId) {
        List<Comment> comments = commentRepository.findByManageLeaveId(manageLeaveId);
        return comments.stream()
                .map(this::convertToDTOWithEmployee)
                .collect(Collectors.toList());
    }

    private CommentEmployeeDto convertToDTOWithEmployee(Comment comment) {
        CommentEmployeeDto dto = modelMapper.map(comment, CommentEmployeeDto.class);

        // Fetch and set employee details
        if (comment.getEmpId() != null) {
            Employees employee = employeesRepository.findById(comment.getEmpId()).orElse(null);
            if (employee != null) {
                dto.setEmployeeId(employee.getEmpId());
                dto.setEmployeeName(employee.getFullName());
                dto.setEmployeePhoto(employee.getPhoto());
            }
        }

        return dto;
    }
    public List<CommentEmployeeDto> getAllCommentEmployeeDTOs() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CommentEmployeeDto convertToDTO(Comment comment) {
        CommentEmployeeDto dto = modelMapper.map(comment, CommentEmployeeDto.class);

        // Explicitly set properties from associated Employees entity
        if (comment.getEmpId() != null) {
            Employees employee = employeesRepository.findById(comment.getEmpId()).orElse(null);
            if (employee != null) {
                dto.setEmployeeId(employee.getEmpId());
                dto.setEmployeeName(employee.getFullName());
                dto.setEmployeePhoto(employee.getPhoto());
            }
        }

        return dto;
    }
}
