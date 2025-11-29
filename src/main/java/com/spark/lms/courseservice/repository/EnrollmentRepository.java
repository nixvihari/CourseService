package com.spark.lms.courseservice.repository;

import com.spark.lms.courseservice.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(String studentId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.courseId = :courseId")
    Integer countStudentByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Enrollment e WHERE e.courseId = :courseId AND e.studentId = :studentId")
    Boolean isEnrolled(@Param("courseId") Long courseId, @Param("studentId") String studentId);
}