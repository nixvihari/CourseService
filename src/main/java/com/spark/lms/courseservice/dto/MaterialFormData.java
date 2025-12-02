
package com.spark.lms.courseservice.dto;

public class MaterialFormData {

    private String title;
    private Long courseId;

    public MaterialFormData() {}

    public MaterialFormData(String title, Long courseId) {
        this.title = title;
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
