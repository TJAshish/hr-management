package com.hrmanagement.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrmanagement.entities.FileStorage;


public interface FileStorageRepository extends JpaRepository<FileStorage, UUID> {

	Optional<FileStorage> findByFileIdAndType(UUID imageId, String type);

	List<FileStorage> findByFileIdAndTypeIn(UUID fileId, List<String> type);

}

