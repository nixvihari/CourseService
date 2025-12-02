package com.spark.lms.courseservice.repository;

import com.spark.lms.courseservice.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
    // Find all materials for a specific course
    List<CourseMaterial> findByCourseId(Long courseId);
}
