package com.hrmanagement.controllers;

import com.hrmanagement.dtos.CommentEmployeeDto;
import com.hrmanagement.entities.Comment;
import com.hrmanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")  
public class CommentController {

	@Autowired
    private CommentService commentService;

//    @GetMapping
//    public ResponseEntity<List<Comment>> getAllComments() {
//        List<Comment> comments = commentService.getAllComments();
//        return ResponseEntity.ok(comments);
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<Page<Comment>> getAllCommentsPaged(@RequestParam(defaultValue = "0") int page,
//                                                             @RequestParam(defaultValue = "10") int size) {
//        Page<Comment> comments = commentService.getAllCommentsWithPagination(page, size);
//        return ResponseEntity.ok(comments);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Comment> getCommentById(@PathVariable int id) {
//        Comment comment = commentService.getCommentById(id);
//        return ResponseEntity.ok(comment);
//    }
    @GetMapping("/dtos")
    public List<CommentEmployeeDto> getAllCommentEmployeeDTOs() {
        return commentService.getAllCommentEmployeeDTOs();
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Comment> updateComment(@PathVariable int id, @RequestBody Comment updatedComment) {
//        Comment updated = commentService.updateComment(id, updatedComment);
//        if (updated != null) {
//            return ResponseEntity.ok(updated);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
//        commentService.deleteComment(id);
//        return ResponseEntity.noContent().build();
//    }
    @GetMapping("/{manageLeaveId}")
    public List<CommentEmployeeDto> getAllCommentsByManageLeaveId(@PathVariable Integer manageLeaveId) {
        return commentService.getAllCommentsByManageLeaveId(manageLeaveId);
    }
}
