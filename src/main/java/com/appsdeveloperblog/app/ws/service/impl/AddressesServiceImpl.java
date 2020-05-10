package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.exception.CustomExceptionHandler;
import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.AddressRepository;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.AddressesService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDto;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

@Service
public class AddressesServiceImpl implements AddressesService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressRepository addressRepository;

	@Override
	public List<AddressDto> getUserAddresses(String userId) {
		List<AddressDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();

		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if (userEntity == null)
			return returnValue;

		Iterable<AddressEntity> addressesIterable = addressRepository.findAllByUserDetails(userEntity);
		for (AddressEntity addressEntity : addressesIterable) {
			returnValue.add(modelMapper.map(addressEntity, AddressDto.class));
		}

		return returnValue;
	}

	@Override
	public List<AddressDto> getAddresses() {
		
		List<AddressEntity> listAddressesEntity = new ArrayList<AddressEntity>();
		List<AddressDto> listAddressesDto = new ArrayList<AddressDto>();
		ModelMapper modelMapper = new ModelMapper();

		 
		listAddressesEntity =   (List<AddressEntity>) addressRepository.findAll();
		
		if(listAddressesEntity.isEmpty()) throw new CustomExceptionHandler(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


		for (AddressEntity addressEntity : listAddressesEntity) {
			if(addressEntity.getCity().equalsIgnoreCase("Ahmedabad")) {
				addressEntity.setCity("amdavad");
				
//				addressEntity.getUserDetails().getUserId();
			}
			
			listAddressesDto.add(modelMapper.map(addressEntity, AddressDto.class));
		}
		
		
		return listAddressesDto;
	}

}
