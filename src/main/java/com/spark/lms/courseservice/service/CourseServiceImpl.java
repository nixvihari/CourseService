package com.spark.lms.courseservice.service;

import com.spark.lms.courseservice.CourseServiceApplication;
import com.spark.lms.courseservice.dto.CourseDTO;
import com.spark.lms.courseservice.dto.CourseResponseDTO;
import com.spark.lms.courseservice.dto.StudentCourseDTO;
import com.spark.lms.courseservice.entity.Course;
import com.spark.lms.courseservice.entity.Enrollment;
import com.spark.lms.courseservice.repository.CourseRepository;
import com.spark.lms.courseservice.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseServiceApplication courseServiceApplication;

	private final CourseRepository courseRepo;
	private final EnrollmentRepository enrollmentRepo;

	public CourseServiceImpl(CourseRepository courseRepo, EnrollmentRepository enrollmentRepo,
			CourseServiceApplication courseServiceApplication) {
		this.courseRepo = courseRepo;
		this.enrollmentRepo = enrollmentRepo;
		this.courseServiceApplication = courseServiceApplication;
	}

	@Override
	public List<StudentCourseDTO> getEnrolledCourses(String studentId) {

		List<Enrollment> enrollments = enrollmentRepo.findByStudentId(studentId);

		List<Long> courseIds = enrollments.stream().map(Enrollment::getCourseId).collect(Collectors.toList());

		List<Course> courses = courseRepo.findAllById(courseIds);

		return courses.stream().map(c -> new StudentCourseDTO(c.getId(), c.getTitle(), c.getDescription()))
				.collect(Collectors.toList());
	}

	@Override
	public Course addCourse(CourseDTO dto, String creatorId) {
		Course course = new Course(dto.getTitle(), dto.getDescription(), creatorId);
		return courseRepo.save(course);
	}

	@Override
	public Course getCourseById(Long id) {
		return courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
	}

	@Override
	public Course updateCourse(Long id, CourseDTO dto) {
		Course course = getCourseById(id);

		course.setTitle(dto.getTitle());
		course.setDescription(dto.getDescription());

		return courseRepo.save(course);
	}

	@Override
	public void deleteCourse(Long id) {
		courseRepo.deleteById(id);
	}

	@Override
	public List<CourseResponseDTO> getCourses() {
		List<Course> courses = courseRepo.findAll();
		return courses.stream().map(course -> new CourseResponseDTO(course.getId(),course.getTitle(),course.getDescription()))
				.collect(Collectors.toList());
	}
}
