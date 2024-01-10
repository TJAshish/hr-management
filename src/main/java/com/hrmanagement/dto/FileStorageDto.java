package com.hrmanagement.dto;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageDto {
	private String location;
	private String dir;
	private String type;
	private UUID fileId;
	private String fileName;
	MultipartFile file;
	private boolean isContent;
}

