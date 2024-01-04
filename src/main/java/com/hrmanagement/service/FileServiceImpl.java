package com.hrmanagement.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
    	try {
    	    // Validate if the file is not null and is not empty
    	    if (file == null || file.isEmpty()) {
    	        throw new IllegalArgumentException("Please provide a valid image file.");
    	    }

    	    // Validate if the file is an image
    	    if (!isImage(file.getContentType())) {
    	        throw new IllegalArgumentException("Invalid image file format.");
    	    }

    	    // File name
    	    String name = file.getOriginalFilename();

    	    // Random name generate file
    	    String randomId = UUID.randomUUID().toString();
    	    String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

    	    // Full path
    	    String filePath = Paths.get(path, fileName1).toAbsolutePath().toString();

    	    // Create folder if not created
    	    Files.createDirectories(Paths.get(path));

    	    // Copy input stream to file using Java NIO
    	    Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

    	    return fileName1;
    	} catch (IOException e) {
    	    throw new IOException("Error uploading the image: " + e.getMessage(), e);
    	}
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = Paths.get(path, fileName).toAbsolutePath().toString();
        return new FileInputStream(fullPath);
    }

    // Validation method to check if the content type represents an image
    private boolean isImage(String contentType) {
        return contentType != null && contentType.startsWith("image/");
    }
}
