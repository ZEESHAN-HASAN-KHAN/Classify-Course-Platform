package com.example.course_service.controller;

import antlr.ASTNULLType;
import com.example.course_service.model.Course;
import com.example.course_service.repository.CourseRepository;
import com.example.course_service.repository.EnrollmentRepository;
import com.example.course_service.service.CourseService;
import com.example.course_service.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  com.example.course_service.model.Enrollment;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService service;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    EnrollmentRepository enrollmentRepo;
    @GetMapping
    public ResponseEntity<List<Course>> getCourses(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);

        if (!jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String role = jwtUtil.extractRole(token);
        System.out.println("ROLE: " + role); // 🔍 Debug print

        return ResponseEntity.ok(courseRepository.findAll());
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id)
    {
        return service.getCourse(id);
    }
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course,
                                          @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if (!jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }

        String role = jwtUtil.extractRole(token);
        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only ADMINs can create courses");
        }

        return ResponseEntity.ok(courseRepository.save(course));
    }
    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<?> enrollInCourse(@PathVariable Long courseId,
                                            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        if (!jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        Claims claims = jwtUtil.extractClaims(token);
        Long userId = Long.valueOf(claims.get("userId").toString());
        String role = claims.get("role", String.class);

        if (!"USER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only USER can enroll");
        }


        if (enrollmentRepo.existsByUserIdAndCourseId(userId, courseId)) {
            return ResponseEntity.badRequest().body("Already enrolled");
        }

        Enrollment enroll = new Enrollment();
        enroll.setUserId(userId);
        enroll.setCourseId(courseId);

        enrollmentRepo.save(enroll);
        return ResponseEntity.ok("Enrolled successfully");
    }

    @GetMapping("/my-courses")
    public ResponseEntity<?> getEnrolledCourses(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if (!jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        Claims claims = jwtUtil.extractClaims(token);
        Long userId = Long.valueOf(claims.get("userId").toString());

        List<Enrollment> enrollments = enrollmentRepo.findByUserId(userId);
        return ResponseEntity.ok(enrollments);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id)
    {
        service.deleteCourse(id);
    }
}
