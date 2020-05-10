package com.appsdeveloperblog.app.ws.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.repositories.AddressRepository;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.AddressService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDto;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public AddressDto getAddressDetail(String addressId) {
		 AddressDto addressDto = null;

		 AddressEntity addressEntity  =  addressRepository.findByAddressId(addressId);
		 
		 if(addressEntity != null) {
			 ModelMapper modelMapper = new ModelMapper();
			 
			 addressDto = modelMapper.map(addressEntity,AddressDto.class);
		
		 }
		 
			
		return addressDto;
	}
	
	

}
