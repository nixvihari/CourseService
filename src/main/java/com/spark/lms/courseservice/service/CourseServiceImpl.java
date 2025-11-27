package com.spark.lms.courseservice.service;

import com.spark.lms.courseservice.dto.StudentCourseDTO;
import com.spark.lms.courseservice.entity.Course;
import com.spark.lms.courseservice.entity.Enrollment;
import com.spark.lms.courseservice.repository.CourseRepository;
import com.spark.lms.courseservice.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;

    public CourseServiceImpl(CourseRepository courseRepo,
                             EnrollmentRepository enrollmentRepo) {
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    @Override
    public List<StudentCourseDTO> getEnrolledCourses(Long studentId) {
        
        List<Enrollment> enrollments = enrollmentRepo.findByStudentId(studentId);

       
        List<Long> courseIds = enrollments.stream()
                .map(Enrollment::getCourseId)
                .collect(Collectors.toList());

       
        List<Course> courses = courseRepo.findAllById(courseIds);

        
        return courses.stream()
                .map(c -> new StudentCourseDTO(c.getId(), c.getTitle(), c.getDescription()))
                .collect(Collectors.toList());
    }
}
