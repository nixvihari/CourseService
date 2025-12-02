package com.spark.lms.courseservice.CourseRepository;

import com.spark.lms.courseservice.entity.Course;
import com.spark.lms.courseservice.repository.CourseRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest                    // Only loads JPA components (perfect for repo tests)
@ActiveProfiles("test")        // Use application-test.yml or in-memory H2
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldSaveAndFindCourseById() {
        // Given
        Course course = new Course("Java Basics", "Introduction to Java", 99L);
        Course saved = courseRepository.save(course);

        // When
        Optional<Course> found = courseRepository.findById(saved.getId());

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Java Basics");
        assertThat(found.get().getDescription()).isEqualTo("Introduction to Java");
        assertThat(found.get().getCreatedBy()).isEqualTo(99L);
    }

    @Test
    void shouldFindAllCourses_whenMultipleCoursesExist() {
        // Given
        courseRepository.save(new Course("Spring Boot", "Build REST APIs", 1L));
        courseRepository.save(new Course("React JS", "Modern Frontend", 2L));
        courseRepository.save(new Course("DevOps", "CI/CD with Jenkins", 1L));

        // When
        List<Course> allCourses = courseRepository.findAll();

        // Then
        assertThat(allCourses).hasSize(3);
        assertThat(allCourses)
                .extracting(Course::getTitle)
                .containsExactlyInAnyOrder("Spring Boot", "React JS", "DevOps");
    }

    @Test
    void shouldDeleteCourseSuccessfully() {
        // Given
        Course course = new Course("Python 101", "Learn Python", 10L);
        Course saved = courseRepository.save(course);

        // When
        courseRepository.deleteById(saved.getId());
        Optional<Course> deleted = courseRepository.findById(saved.getId());

        // Then
        assertThat(deleted).isEmpty();
        assertThat(courseRepository.count()).isZero();
    }
}