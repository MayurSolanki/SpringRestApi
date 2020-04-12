package com.appsdeveloperblog.app.ws.ui.model.request;

import javax.persistence.Column;

public class DepartmentRequestModel {

	private String departmentName;
	
	private String departmentType;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}    
	
}
