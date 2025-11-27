package com.spark.lms.courseservice.controller;

import com.spark.lms.courseservice.dto.CourseDTO;
import com.spark.lms.courseservice.dto.StudentCourseDTO;
import com.spark.lms.courseservice.entity.Course;
import com.spark.lms.courseservice.service.CourseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    
    @GetMapping("/enrolledCourses")
    public ResponseEntity<List<StudentCourseDTO>> getEnrolledCourses(
            @RequestHeader("userId") Long studentId,
            @RequestHeader("role") String role
    ) {

        if (!"STUDENT".equalsIgnoreCase(role)) {
            return ResponseEntity.status(403).build();
        }

        List<StudentCourseDTO> enrolledCourses = courseService.getEnrolledCourses(studentId);
        return ResponseEntity.ok(enrolledCourses);
    }

    
    @PostMapping
    public ResponseEntity<Course> addCourse(
            @RequestBody CourseDTO dto,
            @RequestHeader("userId") Long creatorId,
            @RequestHeader("role") String role
    ) {
        if (!role.equals("TEACHER") && !role.equals("ADMIN")) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(courseService.addCourse(dto, creatorId));
    }

    // GET COURSE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // UPDATE COURSE
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseDTO dto,
            @RequestHeader("role") String role
    ) {
        if (!role.equals("TEACHER") && !role.equals("ADMIN")) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }

    // DELETE COURSE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id,
            @RequestHeader("role") String role
    ) {
        if (!role.equals("ADMIN")) {
            return ResponseEntity.status(403).build();
        }

        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
