package br.com.project.api.taskmaster.service.project;

import br.com.project.api.taskmaster.builder.project.ProjectBuilder;
import br.com.project.api.taskmaster.command.project.CreateProjectCommand;
import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.utils.user.GenerationTimestamp;

public class ProjectCreator {

	private final GenerationTimestamp generationTimestamp;
	
	public ProjectCreator(GenerationTimestamp generationTimestamp) {
		this.generationTimestamp = generationTimestamp;
	}



	public ProjectModel createProjectAttributes(CreateProjectCommand createProjectCommand) {
		return new ProjectBuilder()
				.name(createProjectCommand.getName())
				.description(createProjectCommand.getDescription())
				.createdAt(generationTimestamp.timestampGenerator())
				.updatedAt(generationTimestamp.timestampGenerator())
				.startDate(createProjectCommand.getStartDate())
				.deadlineDate(createProjectCommand.getDeadlineDate())
				.priority(createProjectCommand.getPriority())
				.status(createProjectCommand.getStatus())
				.build();
	}
}
