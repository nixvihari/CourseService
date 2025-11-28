package com.spark.lms.courseservice.dto;

public class CourseResponseDTO {

	private Long courseId;
	private String title;
	private String description;

	public CourseResponseDTO() {
	}

	public CourseResponseDTO(Long courseId, String title, String description) {
		this.courseId = courseId;
		this.title = title;
		this.description = description;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
