package br.com.project.api.taskmaster.dto.project;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.project.api.taskmaster.model.project.ProjectModel;

public class ProjectResponseDTO {
	
	private UUID id;
	private String name;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime startDate;
	private LocalDateTime deadlineDate;
	private String priority;// ver o tipo de prioridade.
	private String status;
	private UUID userId;

	//Converts an object from the ProjectModel entity to a CreateProjectCommand.
	//Returns only the desired data without loading all the user project data.
	public ProjectResponseDTO(ProjectModel project) {
		this.id = project.getId();
	    this.name = project.getName() != null ? project.getName() : "No Name";
	    this.description = project.getDescription() != null ? project.getDescription() : "No Description";
	    this.createdAt = project.getCreatedAt();
	    this.updatedAt = project.getUpdatedAt();
	    this.startDate = project.getStartDate();
	    this.deadlineDate = project.getDeadlineDate();
	    this.priority = project.getPriority() != null ? project.getPriority() : "Low";
	    this.status = project.getStatus() != null ? project.getStatus() : "Pending";
	    this.userId = project.getUser() != null ? project.getUser().getId() : null;
		}

	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(LocalDateTime deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
}
