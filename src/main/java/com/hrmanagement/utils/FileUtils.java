package com.hrmanagement.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {
	public static int saveImages(String location, String fileName, MultipartFile photo) {
		int status = 0;
		String finalPath = location;
		File folder = new File(finalPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		finalPath += File.separator + fileName;
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(finalPath)))) {
			FileCopyUtils.copy(photo.getInputStream(), stream);

		} catch (Exception e) {
			status = 1;
			e.printStackTrace();
		}
		return status;
	}

	public static String path(String... arg) {
		StringBuilder sb = new StringBuilder();
		for (String a : arg) {
			sb.append(a).append(File.separator);
		}
		return sb.toString();
	}

}

