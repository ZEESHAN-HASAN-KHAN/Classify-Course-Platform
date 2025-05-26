package com.example.course_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.course_service.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
