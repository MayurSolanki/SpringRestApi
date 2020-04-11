package com.appsdeveloperblog.app.ws.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appsdeveloperblog.app.ws.shared.dto.UserProfileImageDto;

public interface FileStorageService {

	UserProfileImageDto storeFile(MultipartFile multipartFile, String userId);
	
    Resource loadFileAsResource(String fileName);
}
