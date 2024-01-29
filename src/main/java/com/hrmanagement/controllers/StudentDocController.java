package com.hrmanagement.controllers;

import com.hrmanagement.entities.StudentDoc;
import com.hrmanagement.service.FileService;
import com.hrmanagement.service.StudentDocService;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/student-docs")
public class StudentDocController {

	@Autowired
    private StudentDocService studentDocService;
	
	@Autowired
	private FileService fileService;

	@Value("${apis.doc}")
	private String path;
    

    @PostMapping("/{clientId}")
    public ResponseEntity<StudentDoc> createStudentDoc(
            @RequestBody StudentDoc studentDoc,
            @PathVariable Integer clientId) {
        StudentDoc createdDoc = studentDocService.create(studentDoc, clientId);
        return new ResponseEntity<>(createdDoc, HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Page<StudentDoc>> getAllStudentdoc(
            @PathVariable("clientId") Integer clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<StudentDoc> studentDocPage = studentDocService.getStudentDocsByClientId(clientId, pageable);

        return ResponseEntity.ok(studentDocPage);
    }

    @GetMapping("/{id}/{clientId}")
    public ResponseEntity<StudentDoc> getStudentDocByIdAndClientId(
            @RequestParam Integer id,
            @RequestParam Integer clientId) {
        StudentDoc studentDoc = studentDocService.getStudentDocByIdAndClientId(id, clientId);
        return studentDoc != null ?
                new ResponseEntity<>(studentDoc, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/{clientId}")
    public ResponseEntity<StudentDoc> updateStudentDoc(
            @PathVariable Integer id,
            @PathVariable Integer clientId,
            @RequestBody StudentDoc updatedDoc) {
        StudentDoc updated = studentDocService.update(id, clientId, updatedDoc);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{clientId}")
    public ResponseEntity<Void> deleteStudentDocByIdAndClientId(
            @PathVariable Integer id,
            @PathVariable Integer clientId) {
        studentDocService.deleteStudentDocByIdAndClientId(id, clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Method to serve files for StudentDoc
    @GetMapping("/{id}/{clientId}/{docName}")
    public ResponseEntity<byte[]> downloadDoc(
            @Valid @PathVariable Integer id,
            @PathVariable Integer clientId,
            @PathVariable("docName") String docName) throws IOException {

        InputStream resource = fileService.getResource(path, docName);
        byte[] fileContent = StreamUtils.copyToByteArray(resource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", docName);

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<StudentDoc>> searchDetais(@RequestParam("query") String query,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(studentDocService.searchDetails(query,pageable));
    }
    @PostMapping("/doc/upload/{id}/{clientId}")
	public ResponseEntity<StudentDoc> uploadDoc(
			@RequestParam ("doc") MultipartFile doc,
			@PathVariable Integer id,
			@PathVariable Integer clientId
			)throws IOException
	{
		StudentDoc std = this.studentDocService.getStudentDocByIdAndClientId(id, clientId);
		String fileName = this.fileService.uploadDoc(path, doc);
		std.setFileUrl(fileName);
		StudentDoc updateStd = this.studentDocService.update(id,clientId, std);
		return new ResponseEntity<StudentDoc>(updateStd,HttpStatus.OK);
	}

}
