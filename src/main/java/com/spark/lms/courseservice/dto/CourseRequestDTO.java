package com.spark.lms.courseservice.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseRequestDTO {

	private Long courseId;

	@NotBlank
	private String title;

	private String description;

	@NotNull
	@Min(value = 0)
	private Integer courseDuration;

	private List<String> learningObjectives;

	public CourseRequestDTO() {
	}

	public CourseRequestDTO(@NotNull Long courseId, @NotBlank String title, String description,
			@NotNull @Min(0) Integer courseDuration, List<String> learningObjectives) {
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.courseDuration = courseDuration;
		this.learningObjectives = learningObjectives;
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

	public Integer getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(Integer courseDuration) {
		this.courseDuration = courseDuration;
	}

	public List<String> getLearningObjectives() {
		return learningObjectives;
	}

	public void setLearningObjectives(List<String> learningObjectives) {
		this.learningObjectives = learningObjectives;
	}

}
