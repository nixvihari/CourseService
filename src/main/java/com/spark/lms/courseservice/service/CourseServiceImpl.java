package com.spark.lms.courseservice.service;

import com.spark.lms.courseservice.CourseServiceApplication;
import com.spark.lms.courseservice.dto.CourseDTO;
import com.spark.lms.courseservice.dto.CourseDetailsDTO;
import com.spark.lms.courseservice.dto.CourseRequestDTO;
import com.spark.lms.courseservice.dto.CourseResponseDTO;
import com.spark.lms.courseservice.dto.EnrollmentDTO;
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
	public Course addCourse(CourseRequestDTO dto, String creatorId) {
		Course course = new Course(dto.getTitle(), dto.getDescription(), dto.getCourseDuration(),
				dto.getLearningObjectives(), creatorId);
		return courseRepo.save(course);
	}

	@Override
	public CourseDetailsDTO getCourseById(Long id, String studentId) {
		Course foundCourse = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
		Integer studentsEnrolled = enrollmentRepo.countStudentByCourseId(foundCourse.getId());
		Boolean isEnrolled = enrollmentRepo.isEnrolled(id, studentId);
		
		CourseDetailsDTO courseDetails = new CourseDetailsDTO(
		    id,
		    foundCourse.getTitle(),
		    foundCourse.getDescription(),
		    foundCourse.getCourseDuration(),
		    foundCourse.getLearningObjectives(),
		    studentsEnrolled,
		    isEnrolled,
		    null
		);
		return courseDetails;
	}

	@Override
	public Course updateCourse(Long id, CourseDTO dto) {
		Course course = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

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
		return courses.stream()
				.map(course -> new CourseResponseDTO(course.getId(), course.getTitle(), course.getDescription()))
				.collect(Collectors.toList());
	}
	
	
	@Override
	public CourseDetailsDTO enrollStudent(Long courseId, String studentId) {

	    Course course = courseRepo.findById(courseId)
	            .orElseThrow(() -> new RuntimeException("Course not found"));

	    Boolean alreadyEnrolled = enrollmentRepo.isEnrolled(courseId, studentId);

	    if (alreadyEnrolled) {
	        // return null so controller can send 204 No Content
	        return null;
	    }

	    Enrollment enrollment = new Enrollment(
	            courseId,
	            studentId,
	            java.time.LocalDateTime.now()
	    );
	    enrollmentRepo.save(enrollment);
	    
	    CourseDetailsDTO response = getCourseById(courseId, studentId);

	    return response;
	}

}
