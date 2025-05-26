package com.example.course_service.controller;

import com.example.course_service.model.Course;
import com.example.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService service;

    @GetMapping
    public List<Course> getAllCourses(){
        return service.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id)
    {
        return service.getCourse(id);
    }
    @PostMapping
    public Course addCourse(@RequestBody Course course)
    {
        return service.addCourse(course);
    }
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id)
    {
        service.deleteCourse(id);
    }
}
