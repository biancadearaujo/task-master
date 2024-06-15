package br.com.project.api.taskmaster.builder.project;

import java.time.LocalDateTime;

import br.com.project.api.taskmaster.model.project.ProjectModel;

public class ProjectBuilder {

	private String name;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime startDate;
	private LocalDateTime deadlineDate;
	private String priority;// ver o tipo de prioridade.
	private String status;
	
	//@Column(name = "creator_user_id")
	//private UUID creatorUserId;//id do usuario.

	
	public ProjectBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public ProjectBuilder description(String description) {
		this.description = description;
		return this;
	}
	
	public ProjectBuilder createdAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public ProjectBuilder updatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public ProjectBuilder startDate(LocalDateTime startDate) {
		this.startDate = startDate;
		return this;
	}

	public ProjectBuilder deadlineDate(LocalDateTime deadlineDate) {
		this.deadlineDate = deadlineDate;
		return this;
	}

	public ProjectBuilder priority(String priority) {
		this.priority = priority;
		return this;
	}

	public ProjectBuilder status (String status) {
		this.status = status;
		return this;
	}

	public ProjectModel build() {
		return new ProjectModel(
		this.name,
		this.description,
		this.createdAt,
		this.updatedAt,
		this.startDate,
		this.deadlineDate,
		this.priority,
		this.status
		);
	}
	
}
