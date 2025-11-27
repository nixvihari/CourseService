package com.spark.lms.courseservice.service;

import com.spark.lms.courseservice.dto.StudentCourseDTO;
import java.util.List;

public interface CourseService {
    List<StudentCourseDTO> getEnrolledCourses(Long studentId);
}
