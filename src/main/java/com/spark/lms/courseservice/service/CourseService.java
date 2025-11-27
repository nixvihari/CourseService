package com.spark.lms.courseservice.service;

import com.spark.lms.courseservice.dto.CourseDTO;
import com.spark.lms.courseservice.dto.StudentCourseDTO;
import com.spark.lms.courseservice.entity.Course;

import java.util.List;

public interface CourseService {

    List<StudentCourseDTO> getEnrolledCourses(Long studentId);

    Course addCourse(CourseDTO dto, Long creatorId);
    Course getCourseById(Long id);
    Course updateCourse(Long id, CourseDTO dto);
    void deleteCourse(Long id);
}
	