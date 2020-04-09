package com.appsdeveloperblog.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {  //CrudRepository
	
	
	//find
	// By		
	// email  <- column name
	// user_id  <- UserId

	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);

//  ======================================= Native SQL Query ===========================================================	
	
//	@Query(value = "SELECT * FROM `users` WHERE users.first_name = ?1", nativeQuery = true)
//	List<UserEntity> findUserByFirstName(String firstName);      // Positional paramater
	
	
	@Query(value = "SELECT * FROM `users` WHERE users.first_name = :firstName", nativeQuery = true)
	List<UserEntity> findUserByFirstName(@Param("firstName") String firstName);      // Positional paramater
	
	@Query(value= "select u.first_name, u.last_name from users u where first_name LIKE '%keyword%' or last_name LIKE '%keyword%'",nativeQuery= true)
	List<UserEntity> findUserByKeyword(@Param("keyword") String keyword);  // Search Query
	
	
	@Query(value= "select u.first_name, u.last_name from users u where first_name LIKE '%keyword%' or last_name LIKE '%keyword%'",nativeQuery= true)
	List<Object[]> findUserFirstNameAndLastNameByKeyword(@Param("keyword") String keyword);  // Search Query
	
	
	@Transactional   // when any error occured then db will be roll backed
	@Modifying   // Modifying the db
	@Query(value = "update users u set u.EMAIL_VERIFICATION_STATUS = :emailVerficationStatus where u.user_id = :userId ", nativeQuery = true)
	void UpdateUserEmailVerificationStatus(@Param("emailVerficationStatus") boolean emailVerification, @Param("userId") String userId);
	
	

//  ======================================= JPQL (Java Persistence Query Language) ===========================================================	
	
	@Query("select user from UserEntity user where user.userId = :userId")
	UserEntity findUserByUserId(@Param("userId") String userId);
	
	@Query("select user.firstName, user.lastName from UserEntity user where user.userId = :userId")  // get selected column from Table
	List<Object[]> getUserEntityFullNameById(@Param("userId") String userId);
}
