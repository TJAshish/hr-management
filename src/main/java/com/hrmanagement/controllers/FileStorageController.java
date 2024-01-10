package com.hrmanagement.controllers;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrmanagement.dto.FileStorageDto;
import com.hrmanagement.service.FileStorageService;
import com.hrmanagement.config.SchoolConfig;

@RestController
@RequestMapping
public class FileStorageController {

	@Autowired
	FileStorageService fileStorageService;
	@Autowired
	SchoolConfig schoolConfig;

	@PostMapping(value = "/file/{type}/{dir}/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody ResponseEntity<String> uploadRegImage(@PathVariable String id, @PathVariable String type,
			@PathVariable String dir, @RequestParam("file") MultipartFile file) {
		UUID fileId = UUID.nameUUIDFromBytes(id.getBytes());
		if (!file.isEmpty()) {
			return ResponseEntity
					.ok(fileStorageService.createOrUpdateFileStorage(new FileStorageDto(schoolConfig.getImageLocation(),
							dir, type, fileId, file.getOriginalFilename(), file, false)));
		}
		return ResponseEntity.internalServerError().body("Nothing to save");
	}

	@GetMapping(value = "/file/{type}/{dir}/{id}")
	public ResponseEntity<Map<String, String>> viewfile(@PathVariable String id, @PathVariable String dir,
			@PathVariable String type, @RequestParam(name = "content", required = false) boolean content) {
		String location = schoolConfig.getImageLocation() + File.separator + dir;
		Map<String, String> data = fileStorageService.getFileMap(location, UUID.nameUUIDFromBytes(id.getBytes()),
				Arrays.asList(type.split(",")), content);
		return ResponseEntity.ok(data);
	}

}
