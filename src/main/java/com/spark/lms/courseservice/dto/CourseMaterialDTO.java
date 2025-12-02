
package com.spark.lms.courseservice.dto;

import java.time.LocalDateTime;

public class CourseMaterialDTO {

    private Long materialId;
    private Long courseId;
    private String title;
    private String contentUrl;
    private LocalDateTime createdAt;

    public CourseMaterialDTO() { }

    public CourseMaterialDTO(Long materialId, Long courseId, String title, String contentUrl, LocalDateTime createdAt) {
        this.materialId = materialId;
        this.courseId = courseId;
        this.title = title;
        this.contentUrl = contentUrl;
        this.createdAt = createdAt;
    }

    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContentUrl() { return contentUrl; }
    public void setContentUrl(String contentUrl) { this.contentUrl = contentUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
