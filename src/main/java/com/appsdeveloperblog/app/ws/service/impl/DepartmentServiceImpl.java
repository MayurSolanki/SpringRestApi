package com.appsdeveloperblog.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.io.entity.DepartmentEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.DepartmentRepository;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.DepartmentService;
import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;
import com.appsdeveloperblog.app.ws.shared.dto.Utils;

@Service
public class DepartmentServiceImpl implements DepartmentService{


	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;
	

	@Autowired
	DepartmentRepository departmentRepository;

	
	@Override
	public DepartmentDto findByDepartmentId(String departmentId) {
		
		
	  DepartmentEntity departmentEntity = 	departmentRepository.findByDepartmentId(departmentId);
	  
	  DepartmentDto departmentDto = new DepartmentDto();
	  BeanUtils.copyProperties(departmentEntity, departmentDto);

		return departmentDto;
	}


	@Override
	public DepartmentDto addDepartment(DepartmentDto departmentDto) {
		
		
		departmentDto.setDepartmentId(utils.generateAddressId(30));
		  
		DepartmentEntity departmentEntity = new DepartmentEntity();
		BeanUtils.copyProperties(departmentDto, departmentEntity);
		 
		DepartmentEntity  departmentEntityBack =  departmentRepository.save(departmentEntity);     
		
		DepartmentDto departmentDto2 = new DepartmentDto();
		BeanUtils.copyProperties(departmentEntityBack, departmentDto2);
		
		return departmentDto2;
	}


	
	

}
