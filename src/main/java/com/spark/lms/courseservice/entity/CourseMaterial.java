
package com.spark.lms.courseservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_material")
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private String title;

    @Column(name = "content_url", nullable = false, length = 1000)
    private String contentUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public CourseMaterial() { }

    public CourseMaterial(Long courseId, String title, String contentUrl, LocalDateTime createdAt) {
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
