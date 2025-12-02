
package com.spark.lms.courseservice.service;

import com.spark.lms.courseservice.dto.CourseMaterialDTO;
import com.spark.lms.courseservice.dto.MaterialFormData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseMaterialService {
    CourseMaterialDTO addMaterial(Long courseId,
                                  Long userId,
                                  String role,
                                  MaterialFormData form,
                                  MultipartFile file);

    List<CourseMaterialDTO> getMaterialsByCourseId(Long courseId);
}
