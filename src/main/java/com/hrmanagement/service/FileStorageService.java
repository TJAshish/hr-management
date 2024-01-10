package com.hrmanagement.service;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.hrmanagement.dto.FileStorageDto;


public interface FileStorageService {

	public Map<String, String> getFileMap(String location, UUID fileId, List<String> types, boolean content);

	public String createOrUpdateFileStorage(FileStorageDto storageDto);

	public Map<String, String> getFileMap(FileStorageDto storageDto);
}

