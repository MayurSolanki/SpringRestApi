package com.appsdeveloperblog.app.ws.io.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id; 
	
	
	@Column(nullable=false,length =50)
	private String departmentId;
	
	@Column(nullable=false,length =50, unique = true)
	private String departmentName;
	
	@Column(nullable=false,length =20)
	private String departmentType;           // Permanent OR Temporary
	
	
	@OneToMany(fetch = FetchType.LAZY,cascade =  CascadeType.ALL,mappedBy = "department")
	private Collection<UserEntity> users;

		

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

	public Collection<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserEntity> users) {
		this.users = users;
	}
	
	
	
	


	
	
	
	
}
