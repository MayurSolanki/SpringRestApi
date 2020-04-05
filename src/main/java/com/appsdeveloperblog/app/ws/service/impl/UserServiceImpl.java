package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.exception.CustomExceptionHandler;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.shared.dto.Utils;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder cBcryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

//         check in db, wheather email exist or not, see findBy (keyword)
		if (userRepository.findByEmail(userDto.getEmail()) != null) {
			throw new RuntimeException("Record already exist !!!");
		}

		UserEntity userEnity = new UserEntity();

		BeanUtils.copyProperties(userDto, userEnity);

		userEnity.setEncryptedPassword(cBcryptPasswordEncoder.encode(userDto.getPassword()));
		userEnity.setUserId(utils.generateUserId(30));

		UserEntity savedUserEntityInDb = userRepository.save(userEnity);

		UserDto returnUserDto = new UserDto();

		BeanUtils.copyProperties(savedUserEntityInDb, returnUserDto);

		return returnUserDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByUserId(String userId) {

		UserDto userDto = new UserDto();

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {

		UserEntity userEntityByUserId = userRepository.findByUserId(userId);

		if (userEntityByUserId == null)
//			throw new NullPointerException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
			
		throw new CustomExceptionHandler(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage()); // this will trigger 3 rd method in AppExceptionHandler class, for different gson representation


			 

		userEntityByUserId.setFirstName(userDto.getFirstName());   // update only two fields
		userEntityByUserId.setLastName(userDto.getLastName());

		UserEntity updatedUserEntity = userRepository.save(userEntityByUserId);

		UserDto returnUserDto = new UserDto();
		BeanUtils.copyProperties(updatedUserEntity, returnUserDto);

		return returnUserDto;
	}

	@Override
	public void deleteUser(String userId) {
		
		UserEntity userEntityByUserId = userRepository.findByUserId(userId);

		if (userEntityByUserId == null)
			throw new CustomExceptionHandler(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()); // this will trigger 3 rd method in AppExceptionHandler class, for different gson representation

		userRepository.delete(userEntityByUserId);
		
	}

}
