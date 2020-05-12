package com.appsdeveloperblog.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.BookEntity;


import java.lang.String;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>,CustomRepository {
	
	BookEntity findByBookName(String bookname); // find specific Book, Book is always Unique

//    List<BookEntity> findByBookPublishers(String publisherId);
	
	
	//SELECT book.*, publisher.* FROM book INNER JOIN book_publisher on book.id = book_publisher.book_id INNER JOIN publisher on publisher.id = book_publisher.publisher_id WHERE publisher.publisher_id = "x83iYRtT4U5QSimA1Kc9DayRY8NUvF"



}
