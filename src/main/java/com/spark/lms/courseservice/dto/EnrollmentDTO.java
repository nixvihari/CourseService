package com.spark.lms.courseservice.dto;

public class EnrollmentDTO {

    private Long courseId;
    private String studentId;

    public EnrollmentDTO() {}

    public EnrollmentDTO(Long courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}
