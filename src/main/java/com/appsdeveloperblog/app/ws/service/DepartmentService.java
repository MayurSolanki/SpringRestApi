package com.appsdeveloperblog.app.ws.service;

import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;

public interface DepartmentService {
	
	   DepartmentDto addDepartment(DepartmentDto departmentDto);
	   DepartmentDto findByDepartmentId(String departmentId);
	

}
