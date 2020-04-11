package com.appsdeveloperblog.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserProfileImageEntity;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfileImageEntity, Long>{
	
	UserProfileImageEntity findByUserProfileImageUrl(String fileName);

	UserProfileImageEntity findByUserDetails(UserEntity userdetails);

}

