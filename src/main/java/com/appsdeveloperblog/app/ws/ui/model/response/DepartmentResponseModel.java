package com.appsdeveloperblog.app.ws.ui.model.response;

import javax.persistence.Column;

public class DepartmentResponseModel {

	private String departmentId;
	private String departmentName;
    private String departmentType;
    
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
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
