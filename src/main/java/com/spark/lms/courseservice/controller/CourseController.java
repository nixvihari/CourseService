package com.spark.lms.courseservice.controller;

import com.spark.lms.courseservice.dto.CourseDTO;
import com.spark.lms.courseservice.dto.CourseDetailsDTO;
import com.spark.lms.courseservice.dto.CourseRequestDTO;
import com.spark.lms.courseservice.dto.CourseResponseDTO;
import com.spark.lms.courseservice.dto.StudentCourseDTO;
import com.spark.lms.courseservice.entity.Course;
import com.spark.lms.courseservice.service.CourseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    //Get Courses
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getCourses() {
    	List<CourseResponseDTO> courseList = courseService.getCourses();
    	return ResponseEntity.ok(courseList);
    }

    // Get Enrolled Courses
    @GetMapping("/enrolledCourses")
    public ResponseEntity<List<StudentCourseDTO>> getEnrolledCourses(
            @RequestHeader("X-User-Id") String studentId,
            @RequestHeader("X-Role") String role
    ) {

        if (!"STUDENT".equalsIgnoreCase(role)) {
            return ResponseEntity.status(403).build();
        }

        List<StudentCourseDTO> enrolledCourses = courseService.getEnrolledCourses(studentId);
        return ResponseEntity.ok(enrolledCourses);
    }

    //Add Course
    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(
            @RequestBody CourseRequestDTO dto,
            @RequestHeader("X-User-Id") String creatorId,
            @RequestHeader("X-Role") String role
    ) {
        if (!role.equalsIgnoreCase("TEACHER") && !role.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(courseService.addCourse(dto, creatorId));
    }

    // GET COURSE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailsDTO> getCourseById(
    		@PathVariable Long id,
    		@RequestHeader("X-User-Id") String studentId,
            @RequestHeader("X-Role") String role
    		) {
        return ResponseEntity.ok(courseService.getCourseById(id, studentId));
    }

    // UPDATE COURSE
    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseDTO dto,
            @RequestHeader("X-Role") String role
    ) {
        if (!role.equalsIgnoreCase("TEACHER") && !role.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }

    // DELETE COURSE
    @DeleteMapping("deleteCourse/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id,
            @RequestHeader("X-Role") String role
    ) {
        if (!role.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).build();
        }

        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
