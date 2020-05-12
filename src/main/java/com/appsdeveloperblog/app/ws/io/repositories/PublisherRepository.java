package com.appsdeveloperblog.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.PublisherEntity;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity, Long>, CustomRepository{

	PublisherEntity findByPublisherName(String name);
	
}
