package com.appsdeveloperblog.app.ws.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appsdeveloperblog.app.ws.FileStorageProperties;
import com.appsdeveloperblog.app.ws.exception.CustomExceptionHandler;
import com.appsdeveloperblog.app.ws.service.FileStorageService;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import org.springframework.util.StringUtils;

@Service
public class FileStorageServiceImpl implements FileStorageService{

	  private final Path fileStorageLocation;

	    @Autowired
	    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	        	throw new CustomExceptionHandler(ErrorMessages.FILE_STORAGE_EXCEPTION.getErrorMessage());
	        }
	    }
	
	@Override
	public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
	        	throw new CustomExceptionHandler(ErrorMessages.FILE_STORAGE_EXCEPTION.getErrorMessage());
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
        	throw new CustomExceptionHandler(ErrorMessages.FILE_STORAGE_EXCEPTION.getErrorMessage());
        }
    }


}
