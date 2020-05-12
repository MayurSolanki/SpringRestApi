package com.appsdeveloperblog.app.ws.io.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.type.IntegerType;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

public class CustomRepositoryImpl implements CustomRepository{
	
	 @PersistenceContext
	 private EntityManager entityManager;

	@Override
	public List<Object[]> findByDepartmentNameCustom(String departmentName) {

		Query query =  entityManager.createNativeQuery("SELECT users.user_id, users.first_name, users.last_name, users.email FROM users LEFT JOIN department on users.department_id = department.id WHERE department.department_name = :departmentNamed ");
		query.setParameter("departmentNamed", departmentName);
		
	    List<Object[]> resultList = (List<Object[]>)query.getResultList();
			 

		return resultList;
	}

	@Override
	public List<Object[]> findAllBooksOfPublisher(String publisherId) {
		Query query =  entityManager.createNativeQuery("SELECT book.book_id, book.book_name FROM book INNER JOIN book_publisher on book.id = book_publisher.book_id INNER JOIN publisher on publisher.id = book_publisher.publisher_id WHERE publisher.publisher_id = :publisherId ");

		query.setParameter("publisherId", publisherId);
		
	    List<Object[]> resultList = (List<Object[]>)query.getResultList();

		
		return resultList;
	}

	@Override
	public List<Object[]> findAllPublisherOfBook(String bookName) {
		
		Query query =  entityManager.createNativeQuery("SELECT publisher.publisher_id, publisher.publisher_name FROM publisher INNER JOIN book_publisher on publisher.id = book_publisher.publisher_id INNER JOIN book on book.id = book_publisher.book_id WHERE book.book_name = :bookName ");

		query.setParameter("bookName", bookName);
		
	    List<Object[]> resultList = (List<Object[]>)query.getResultList();

		
		return resultList;
	}
	
	
	//SELECT publisher.publisher_name FROM publisher INNER JOIN book_publisher on publisher.id = book_publisher.publisher_id INNER JOIN book on book.id = book_publisher.book_id WHERE book.book_name = "Physics"

	
	
	

}
