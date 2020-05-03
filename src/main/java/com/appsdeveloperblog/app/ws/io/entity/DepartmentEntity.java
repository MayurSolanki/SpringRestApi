package com.appsdeveloperblog.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="department", uniqueConstraints =  @UniqueConstraint(columnNames ="departmentName"))
public class DepartmentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642334453520337677L;


	@Id
	@GeneratedValue
	private long id; 
	
	
	@Column(nullable=false,length =50)
	private String departmentId;
	
	@Column(nullable=false,length =50, unique = true)
	private String departmentName;
	
	@Column(nullable=false,length =20)
	private String departmentType;           // Permanent OR Temporary
	
	
	
	
//	@OneToMany(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL,
//            mappedBy = "departmentRequestModel")
//    private UserEntity userEntityDetailss;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
	
	

	
//	public UserEntity getUserEntityDetailss() {
//		return userEntityDetailss;
//	}
//
//	public void setUserEntityDetailss(UserEntity userEntityDetailss) {
//		this.userEntityDetailss = userEntityDetailss;
//	}


	
	
	
	
	
}
