package com.spark.lms.courseservice.servicetest;

import com.spark.lms.courseservice.dto.CourseDTO;
import com.spark.lms.courseservice.dto.StudentCourseDTO;
import com.spark.lms.courseservice.entity.Course;
import com.spark.lms.courseservice.entity.Enrollment;
import com.spark.lms.courseservice.exception.CourseNotFoundException;
import com.spark.lms.courseservice.repository.CourseRepository;
import com.spark.lms.courseservice.repository.EnrollmentRepository;
import com.spark.lms.courseservice.service.CourseServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepo;

    @Mock
    private EnrollmentRepository enrollmentRepo;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course1;
    private Course course2;

    @BeforeEach
    void setup() {
        course1 = new Course("Java", "Basics", 100L);
        course1.setId(1L);

        course2 = new Course("Python", "Intro", 100L);
        course2.setId(2L);
    }

    // ---------------------------------------------------------------------
    // getEnrolledCourses
    // ---------------------------------------------------------------------

    @Test
    void testGetEnrolledCourses_WhenStudentHasMultipleEnrollments() {
        Long studentId = 10L;

        Enrollment e1 = new Enrollment();
        e1.setCourseId(1L);
        e1.setStudentId(studentId);
        e1.setEnrolledAt(LocalDateTime.now());

        Enrollment e2 = new Enrollment();
        e2.setCourseId(2L);
        e2.setStudentId(studentId);
        e2.setEnrolledAt(LocalDateTime.now());

        List<Enrollment> enrollments = Arrays.asList(e1, e2);

        when(enrollmentRepo.findByStudentId(studentId)).thenReturn(enrollments);
        when(courseRepo.findAllById(Arrays.asList(1L, 2L)))
                .thenReturn(Arrays.asList(course1, course2));

        List<StudentCourseDTO> result = courseService.getEnrolledCourses(studentId);

        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getTitle());
        assertEquals("Python", result.get(1).getTitle());
    }

    @Test
    void testGetEnrolledCourses_WhenNoEnrollments() {
        Long studentId = 20L;

        when(enrollmentRepo.findByStudentId(studentId))
                .thenReturn(Collections.emptyList());

        List<StudentCourseDTO> result = courseService.getEnrolledCourses(studentId);

        assertTrue(result.isEmpty());
        verify(courseRepo, never()).findAllById(any());
    }

    @Test
    void testGetEnrolledCourses_WhenSomeCoursesMissing() {
        Long studentId = 30L;

        Enrollment e1 = new Enrollment();
        e1.setCourseId(5L);
        e1.setStudentId(studentId);
        e1.setEnrolledAt(LocalDateTime.now());

        Enrollment e2 = new Enrollment();
        e2.setCourseId(6L);
        e2.setStudentId(studentId);
        e2.setEnrolledAt(LocalDateTime.now());

        when(enrollmentRepo.findByStudentId(studentId))
                .thenReturn(Arrays.asList(e1, e2));

        Course existingCourse = new Course("Test", "Desc", 200L);
        existingCourse.setId(5L);

        when(courseRepo.findAllById(Arrays.asList(5L, 6L)))
                .thenReturn(Arrays.asList(existingCourse));

        List<StudentCourseDTO> result = courseService.getEnrolledCourses(studentId);

        assertEquals(1, result.size());
        assertEquals(5L, result.get(0).getId());
    }

    // ---------------------------------------------------------------------
    // addCourse
    // ---------------------------------------------------------------------

    @Test
    void testAddCourse_Success() {
        CourseDTO dto = new CourseDTO("ML", "Machine Learning");
        Long creatorId = 500L;

        Course savedCourse = new Course("ML", "Machine Learning", creatorId);
        savedCourse.setId(99L);

        when(courseRepo.save(any(Course.class))).thenReturn(savedCourse);

        Course result = courseService.addCourse(dto, creatorId);

        assertEquals("ML", result.getTitle());
        assertEquals("Machine Learning", result.getDescription());
        assertEquals(creatorId, result.getCreatedBy());
    }

    @Test
    void testAddCourse_AllowsNullFields() {
        CourseDTO dto = new CourseDTO(null, null);

        Course returned = new Course(null, null, 300L);
        returned.setId(1L);

        when(courseRepo.save(any(Course.class))).thenReturn(returned);

        Course result = courseService.addCourse(dto, 300L);

        assertNull(result.getTitle());
        assertNull(result.getDescription());
    }

    @Test
    void testAddCourse_WhenRepositoryThrowsException() {
        CourseDTO dto = new CourseDTO("X", "Y");
        Long creatorId = 1L;

        when(courseRepo.save(any())).thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class,
                () -> courseService.addCourse(dto, creatorId));
    }

    // ---------------------------------------------------------------------
    // getCourseById
    // ---------------------------------------------------------------------

    @Test
    void testGetCourseById_WhenExists() {
        when(courseRepo.findById(1L)).thenReturn(Optional.of(course1));

        Course result = courseService.getCourseById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testGetCourseById_WhenNotFound() {
        when(courseRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> courseService.getCourseById(1L));
    }

    @Test
    void testGetCourseById_WhenIdIsNull() {
        assertThrows(Exception.class,
                () -> courseService.getCourseById(null));
    }

    // ---------------------------------------------------------------------
    // updateCourse
    // ---------------------------------------------------------------------

    @Test
    void testUpdateCourse_Success() {
        CourseDTO dto = new CourseDTO("Updated", "Updated Desc");

        when(courseRepo.findById(1L)).thenReturn(Optional.of(course1));
        when(courseRepo.save(any())).thenReturn(course1);

        Course result = courseService.updateCourse(1L, dto);

        assertEquals("Updated", result.getTitle());
        assertEquals("Updated Desc", result.getDescription());
    }

    @Test
    void testUpdateCourse_WhenCourseNotFound() {
        when(courseRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> courseService.updateCourse(1L, new CourseDTO("x", "y")));
    }

    @Test
    void testUpdateCourse_AllowsNullFields() {
        CourseDTO dto = new CourseDTO(null, null);

        when(courseRepo.findById(1L)).thenReturn(Optional.of(course1));
        when(courseRepo.save(any())).thenReturn(course1);

        Course updated = courseService.updateCourse(1L, dto);

        assertNull(updated.getTitle());
        assertNull(updated.getDescription());
    }

    // ---------------------------------------------------------------------
    // deleteCourse
    // ---------------------------------------------------------------------

    @Test
    void testDeleteCourse_Success() {
        Long id = 1L;

        when(courseRepo.existsById(id)).thenReturn(true);  // Add this to simulate existence
        doNothing().when(courseRepo).deleteById(id);

        assertDoesNotThrow(() -> courseService.deleteCourse(id));
        verify(courseRepo, times(1)).deleteById(id);
    }

    @Test
    void testDeleteCourse_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            courseService.deleteCourse(null);
        });
    }

    @Test
    void testDeleteCourse_WhenNotFound() {
        assertThrows(CourseNotFoundException.class, () -> {
            courseService.deleteCourse(999L); // any non-existing id
        });
    }
}
