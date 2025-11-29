package com.spark.lms.courseservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseResponseDTO {
	
	@NotNull
	private Long courseId;
	
	@NotBlank
	private String title;
	
	@NotBlank
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
