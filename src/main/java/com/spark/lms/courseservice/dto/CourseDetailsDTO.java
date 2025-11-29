package com.spark.lms.courseservice.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseDetailsDTO {

	@NotNull
	private Long courseId;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@NotNull
	private Integer courseDuration;

	private List<String> learningObjectives;
	private Integer studentsEnrolled;
	private Boolean isEnrolled;
	private Integer progress;

	public CourseDetailsDTO() {
	}

	public CourseDetailsDTO(@NotNull Long courseId, @NotBlank String title, @NotBlank String description,
			@NotNull Integer courseDuration, List<String> learningObjectives, Integer studentsEnrolled,
			Boolean isEnrolled, Integer progress) {
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.courseDuration = courseDuration;
		this.learningObjectives = learningObjectives;
		this.studentsEnrolled = studentsEnrolled;
		this.isEnrolled = isEnrolled;
		this.progress = progress;
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

	public Integer getStudentsEnrolled() {
		return studentsEnrolled;
	}

	public void setStudentsEnrolled(Integer studentsEnrolled) {
		this.studentsEnrolled = studentsEnrolled;
	}

	public Boolean getIsEnrolled() {
		return isEnrolled;
	}

	public void setIsEnrolled(Boolean isEnrolled) {
		this.isEnrolled = isEnrolled;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

}
