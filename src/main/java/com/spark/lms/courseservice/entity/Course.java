package com.spark.lms.courseservice.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Integer courseDuration;

	@ElementCollection
	@CollectionTable(name = "course_learning_objectives", joinColumns = @JoinColumn(name = "course_id"))
	@Column(name = "learning_objectives")
	private List<String> learningObjectives;

	@Column(name = "created_by")
	private String createdBy;

	public Course() {
	}

	public Course(String title, String description, Integer courseDuration, List<String> learningObjectives,
			String createdBy) {
		this.title = title;
		this.description = description;
		this.courseDuration = courseDuration;
		this.learningObjectives = learningObjectives;
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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