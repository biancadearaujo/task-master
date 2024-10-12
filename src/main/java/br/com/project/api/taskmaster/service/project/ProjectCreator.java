package br.com.project.api.taskmaster.service.project;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.builder.project.ProjectBuilder;
import br.com.project.api.taskmaster.command.project.CreateProjectCommand;
import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.repository.project.ProjectRepository;
import br.com.project.api.taskmaster.repository.user.UserRepository;
import br.com.project.api.taskmaster.utils.user.GenerationTimestamp;

@Component
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
					//.user(createProjectCommand.getUser())
					.build();
		}

}
