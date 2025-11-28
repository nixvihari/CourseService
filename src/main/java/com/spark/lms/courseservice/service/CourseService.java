package com.spark.lms.courseservice.service;

import java.util.List;

import com.spark.lms.courseservice.dto.CourseDTO;
import com.spark.lms.courseservice.dto.CourseResponseDTO;
import com.spark.lms.courseservice.dto.StudentCourseDTO;
import com.spark.lms.courseservice.entity.Course;

public interface CourseService {

    List<StudentCourseDTO> getEnrolledCourses(String studentId);

    List<CourseResponseDTO> getCourses();
    Course addCourse(CourseDTO dto,String creatorId);
    Course getCourseById(Long id);
    Course updateCourse(Long id, CourseDTO dto);
    void deleteCourse(Long id);
}
	