package com.appsdeveloperblog.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.DepartmentEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {

	DepartmentEntity findByDepartmentId(String departmentId);

	DepartmentEntity findByDepartmentName(String departmentName);
	
	                 
	

//SELECT users.user_id, users.first_name, users.last_name, users.email FROM users LEFT JOIN department on users.department_id = department.id WHERE department.department_name = "EC"



}
