package com.appsdeveloperblog.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.DepartmentEntity;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long>{
	
	   DepartmentEntity findByDepartmentId(String departmentId);
	   
	   DepartmentEntity findByDepartmentName(String departmentName);
	
	  

}
