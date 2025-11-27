package com.spark.lms.courseservice.controller;

import com.spark.lms.courseservice.dto.StudentCourseDTO;
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
}
