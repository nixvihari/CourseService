package com.spark.lms.courseservice.controller;

import com.spark.lms.courseservice.dto.CourseMaterialDTO;
import com.spark.lms.courseservice.dto.MaterialFormData;
import com.spark.lms.courseservice.service.CourseMaterialService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseMaterialController {

    private final CourseMaterialService materialService;

    public CourseMaterialController(CourseMaterialService materialService) {
        this.materialService = materialService;
    }

    /**
     * Add course material (only teacher)
     * Full URL: POST /courses/{courseId}/materials
     * Headers required: userId, role
     * Form-data: title (text), file (file)
     */
    @PostMapping(path = "/{courseId}/materials", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseMaterialDTO> addMaterial(
            @PathVariable("courseId") Long courseId,
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @RequestParam("title") String title,
            @RequestPart("file") MultipartFile file) {

        // Wrap title in DTO
        MaterialFormData form = new MaterialFormData();
        form.setTitle(title);

        // Call service to handle upload and save
        CourseMaterialDTO dto = materialService.addMaterial(courseId, userId, role, form, file);

        // Return ResponseEntity with created material
        return ResponseEntity.ok(dto);
    }

    /**
     * Get all materials for a course
     * Full URL: GET /courses/{courseId}/materials
     */
    @GetMapping("/{courseId}/materials")
    public ResponseEntity<List<CourseMaterialDTO>> getMaterials(
            @PathVariable("courseId") Long courseId) {

        List<CourseMaterialDTO> list = materialService.getMaterialsByCourseId(courseId);
        return ResponseEntity.ok(list);
    }
}
