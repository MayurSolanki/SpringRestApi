package com.appsdeveloperblog.app.ws.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appsdeveloperblog.app.ws.FileStorageProperties;
import com.appsdeveloperblog.app.ws.exception.CustomExceptionHandler;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserProfileImageEntity;
import com.appsdeveloperblog.app.ws.io.repositories.UserProfileRepository;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.FileStorageService;
import com.appsdeveloperblog.app.ws.shared.dto.UserProfileImageDto;
import com.appsdeveloperblog.app.ws.shared.dto.Utils;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import org.springframework.util.StringUtils;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	Utils utils;

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new CustomExceptionHandler(ErrorMessages.FILE_STORAGE_EXCEPTION.getErrorMessage());
		}
	}

	@Override
	public UserProfileImageDto storeFile(MultipartFile file, String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) {
			throw new CustomExceptionHandler(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}

		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new CustomExceptionHandler(ErrorMessages.FILE_STORAGE_EXCEPTION.getErrorMessage());
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			
			UserProfileImageEntity userProfileImageEntityFromDbEntity = userProfileRepository.findByUserDetails(userEntity);
			
			if(userProfileImageEntityFromDbEntity == null) {
				
				UserProfileImageEntity userProfileImageEntity = new UserProfileImageEntity();
				userProfileImageEntity.setUserProfileImageId(utils.generateUserId(30));
				userProfileImageEntity.setUserProfileImageUrl(fileName);
				userProfileImageEntity.setUserDetails(userEntity);

//				BeanUtils.copyProperties(userProfileImageDto, UserProfileImageEntity.class);

				UserProfileImageEntity userProfileImageEntity2 = userProfileRepository.save(userProfileImageEntity);

				ModelMapper modelMapper = new ModelMapper();
				UserProfileImageDto userProfileImageDto = modelMapper.map(userProfileImageEntity2,UserProfileImageDto.class);

				return userProfileImageDto;
				
				
			}else {
				
				userProfileImageEntityFromDbEntity.setUserProfileImageUrl(fileName);
				userProfileImageEntityFromDbEntity.setUserDetails(userEntity);

//				BeanUtils.copyProperties(userProfileImageDto, UserProfileImageEntity.class);

				UserProfileImageEntity userProfileImageEntity2 = userProfileRepository.save(userProfileImageEntityFromDbEntity);

				ModelMapper modelMapper = new ModelMapper();
				UserProfileImageDto userProfileImageDto = modelMapper.map(userProfileImageEntity2,UserProfileImageDto.class);

				return userProfileImageDto;
				
			}

			
			
			
			

		} catch (IOException ex) {
			throw new CustomExceptionHandler(ErrorMessages.FILE_STORAGE_EXCEPTION.getErrorMessage());
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new CustomExceptionHandler(ErrorMessages.FILE_NOT_FOUND.getErrorMessage());
			}
		} catch (MalformedURLException ex) {

			throw new CustomExceptionHandler(ErrorMessages.FILE_NOT_FOUND.getErrorMessage());
		}
	}

}
