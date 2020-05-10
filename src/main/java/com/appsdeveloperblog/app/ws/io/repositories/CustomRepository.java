package com.appsdeveloperblog.app.ws.io.repositories;

import java.util.List;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

public interface CustomRepository {
	
	List<Object[]>  findByDepartmentNameCustom(String DepartmentName);
	
	List<Object[]>  findBooksOfPublisher(String publisherId);

	

}
