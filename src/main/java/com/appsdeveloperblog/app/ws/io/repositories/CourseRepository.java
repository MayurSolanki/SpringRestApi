package com.appsdeveloperblog.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.CoursesEntity;

@Repository
public interface CourseRepository extends  CrudRepository<CoursesEntity, Long>{

}
