package com.appsdeveloperblog.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
	
	UserEntity findByEmail(String email);
	
	//find
	// By
	// email  <- column name
	// user_id  <- UserId


	UserEntity findByUserId(String userId);
	
}
