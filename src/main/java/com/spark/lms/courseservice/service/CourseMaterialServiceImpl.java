
package com.spark.lms.courseservice.service;

import com.spark.lms.courseservice.dto.CourseMaterialDTO;
import com.spark.lms.courseservice.dto.MaterialFormData;
import com.spark.lms.courseservice.entity.Course;
import com.spark.lms.courseservice.entity.CourseMaterial;
import com.spark.lms.courseservice.repository.CourseMaterialRepository;
import com.spark.lms.courseservice.repository.CourseRepository;
import com.spark.lms.courseservice.util.S3Util;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {

    private final CourseRepository courseRepository;
    private final CourseMaterialRepository materialRepository;
    private final S3Util s3Util; // fallback local uploader

    public CourseMaterialServiceImpl(CourseRepository courseRepository,
                                     CourseMaterialRepository materialRepository,
                                     S3Util s3Util) {
        this.courseRepository = courseRepository;
        this.materialRepository = materialRepository;
        this.s3Util = s3Util;
    }

    @Override
    public CourseMaterialDTO addMaterial(Long courseId, Long userId, String role, MaterialFormData form, MultipartFile file) {

        // Authorization: only TEACHER allowed
        if (role == null || !"TEACHER".equalsIgnoreCase(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only teachers can add course material");
        }

        // Check course exists
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        // Optionally check that userId == course.getCreatedBy() or user is allowed to upload
        // (skip for now or you can enforce teacher ownership)
        // if (!course.getCreatedBy().equals(userId)) { ... }

        // Validate title & file
        if (form == null || form.getTitle() == null || form.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is required");
        }
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is required");
        }

        // Create storage key: course_{courseId}/material_{timestamp}_{originalFilename}
        String original = file.getOriginalFilename();
        String ext = "";
        if (original != null && original.contains(".")) {
            ext = original.substring(original.lastIndexOf('.'));
        }
        String key = String.format("course_%d/material_%d%s",
                courseId, System.currentTimeMillis(), ext);

        // Upload file (local fallback). s3Util returns a public URL string.
        String fileUrl;
        try {
            fileUrl = s3Util.uploadFile(file, key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload file: " + e.getMessage());
        }

        // Persist material
        CourseMaterial material = new CourseMaterial();
        material.setCourseId(courseId);
        material.setTitle(form.getTitle());
        material.setContentUrl(fileUrl);
        material.setCreatedAt(LocalDateTime.now());

        CourseMaterial saved = materialRepository.save(material);

        // Map to DTO
        return new CourseMaterialDTO(
                saved.getMaterialId(),
                saved.getCourseId(),
                saved.getTitle(),
                saved.getContentUrl(),
                saved.getCreatedAt()
        );
    }

    @Override
    public List<CourseMaterialDTO> getMaterialsByCourseId(Long courseId) {
        List<CourseMaterial> materials = materialRepository.findByCourseId(courseId);
        return materials.stream()
                .map(m -> new CourseMaterialDTO(
                        m.getMaterialId(),
                        m.getCourseId(),
                        m.getTitle(),
                        m.getContentUrl(),
                        m.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
