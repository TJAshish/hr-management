package com.hrmanagement.service;

import java.io.File; 
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrmanagement.dto.FileStorageDto;
import com.hrmanagement.entities.FileStorage;
import com.hrmanagement.repositories.FileStorageRepository;
import com.hrmanagement.config.SchoolConfig;
import com.hrmanagement.utils.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
	@Autowired
	FileStorageRepository fileStorageRepo;
	@Autowired
	SchoolConfig schoolConfig;

	@Override
	public Map<String, String> getFileMap(String location, UUID fileId, List<String> types, boolean content) {
		Map<String, String> data = new HashMap<>();

		List<FileStorage> storageList = fileStorageRepo.findByFileIdAndTypeIn(fileId, types);
		storageList.forEach(s -> {
			try {
				if (content) {
					data.put(s.getType(), location + File.separator + s.getFileName());
				} else {
					byte[] fileContent = org.apache.commons.io.FileUtils
							.readFileToByteArray(new File(location + File.separator + s.getFileName()));
					data.put(s.getType(), Base64.getEncoder().encodeToString(fileContent));
				}

			} catch (IOException e) {
				log.error("Error in getting file {}", e);
			}
		});

		return data;
	}

	@Override
	public String createOrUpdateFileStorage(FileStorageDto storageDto) {
		String fileName = storageDto.getFileId() + "_" + storageDto.getFileName();
		String path = FileUtils.path(schoolConfig.getImageLocation(), storageDto.getDir());
		FileUtils.saveImages(path, fileName, storageDto.getFile());
		FileStorage dir = fileStorageRepo.findByFileIdAndType(storageDto.getFileId(), storageDto.getType())
				.orElse(new FileStorage());
		dir.setFileName(fileName);
		dir.setType(storageDto.getType());
		dir.setFileId(storageDto.getFileId());
		fileStorageRepo.save(dir).getId();
		return fileName;
	}

	@Override
	public Map<String, String> getFileMap(FileStorageDto storageDto) {
		Map<String, String> data = new HashMap<>();

		List<FileStorage> storageList = fileStorageRepo.findByFileIdAndTypeIn(storageDto.getFileId(),
				Arrays.asList(storageDto.getType().split(",")));
		storageList.forEach(s -> {
			try {
				if (storageDto.isContent()) {
					data.put(s.getType(), storageDto.getLocation() + File.separator + s.getFileName());
				} else {
					byte[] fileContent = org.apache.commons.io.FileUtils
							.readFileToByteArray(new File(storageDto.getLocation() + File.separator + s.getFileName()));
					data.put(s.getType(), Base64.getEncoder().encodeToString(fileContent));
				}

			} catch (IOException e) {
				log.error("Error in getting file {}", e);
			}
		});

		return data;
	}
}

