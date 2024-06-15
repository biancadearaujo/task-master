package br.com.project.api.taskmaster.command.project;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class CreateProjectCommand {
	
	@NotBlank(message = "Name cannot be null.")
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "deadline_date")
	private LocalDateTime deadlineDate;
	
	@Column(name = "priority")
	private String priority;// ver o tipo de prioridade.
	
	@Column(name = "name")
	private String status;

	//@Column(name = "creator_user_id")
	//private UUID creatorUserId;//id do usuario.

	
	public CreateProjectCommand(@NotBlank(message = "Name cannot be null.") String name, String description,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startDate, LocalDateTime deadlineDate,
			String priority, String status) {
		super();
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDate = startDate;
		this.deadlineDate = deadlineDate;
		this.priority = priority;
		this.status = status;
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
	
}
