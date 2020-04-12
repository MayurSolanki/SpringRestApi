package com.appsdeveloperblog.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.service.DepartmentService;
import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;
import com.appsdeveloperblog.app.ws.ui.model.request.DepartmentRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.DepartmentResponseModel;

@RestController
@RequestMapping("department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public DepartmentResponseModel saveDepartment(@RequestBody DepartmentRequestModel departmentRequestModel) {
		
		DepartmentResponseModel departmentResponseModel = new DepartmentResponseModel();
		
		DepartmentDto departmentDto = new DepartmentDto();
		BeanUtils.copyProperties(departmentRequestModel, departmentDto);
		
		
		DepartmentDto  departmentDto2 =   departmentService.addDepartment(departmentDto);
		
		BeanUtils.copyProperties(departmentDto2, departmentResponseModel);

		
		return departmentResponseModel;
		
	}
	
	

}
