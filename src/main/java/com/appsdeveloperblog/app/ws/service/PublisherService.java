package com.appsdeveloperblog.app.ws.service;


import java.util.List;

import com.appsdeveloperblog.app.ws.shared.dto.PublisherDto;

public interface PublisherService {
	
     List<PublisherDto> getAllPublisherOfBook(String bookname);
}
