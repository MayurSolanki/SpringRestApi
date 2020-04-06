package com.appsdeveloperblog.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {  //CrudRepository
	
	
	//find
	// By
	// email  <- column name
	// user_id  <- UserId

	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);
	
}
