package com.appsdeveloperblog.app.ws.service;

import com.appsdeveloperblog.app.ws.shared.dto.AddressDto;

public interface AddressService {
	
   AddressDto getAddressDetail(String addressId);

}
