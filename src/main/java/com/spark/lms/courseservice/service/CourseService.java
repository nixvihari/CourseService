package com.spark.lms.courseservice.service;

import com.spark.lms.courseservice.dto.CourseDTO;
import com.spark.lms.courseservice.dto.StudentCourseDTO;
import com.spark.lms.courseservice.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<StudentCourseDTO> getEnrolledCourses(String studentId);

    Course addCourse(CourseDTO dto,String creatorId);
    Course getCourseById(Long id);
    Course updateCourse(Long id, CourseDTO dto);
    void deleteCourse(Long id);
}
	