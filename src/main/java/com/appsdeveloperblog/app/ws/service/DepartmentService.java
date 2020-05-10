package com.appsdeveloperblog.app.ws.service;

import java.util.List;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

public interface DepartmentService {
	
	   DepartmentDto addDepartment(DepartmentDto departmentDto);
	   DepartmentDto findByDepartmentId(String departmentId);
	   
	   
	   
                     	

}
