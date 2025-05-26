package com.example.course_service.service;

import com.example.course_service.model.Course;
import com.example.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repo;
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    public Course getCourse(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Course addCourse(Course course) {
        return repo.save(course);
    }

    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }

}
