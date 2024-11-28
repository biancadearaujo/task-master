package br.com.project.api.taskmaster.service.project;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.builder.project.ProjectBuilder;
import br.com.project.api.taskmaster.command.project.CreateProjectCommand;
import br.com.project.api.taskmaster.exception.user.NoRegisteredUsersException;
import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.repository.project.ProjectRepository;
import br.com.project.api.taskmaster.repository.user.UserRepository;
import br.com.project.api.taskmaster.service.user.UserService;
import br.com.project.api.taskmaster.utils.user.GenerationTimestamp;

@Component
public class ProjectCreator {
	
	private final GenerationTimestamp generationTimestamp;
	private final UserService userService;
	private final ProjectRepository projectRepository; //Não sei se vou usar aqui
		
		public ProjectCreator(GenerationTimestamp generationTimestamp, UserService userService, ProjectRepository projectRepository) {
			this.generationTimestamp = generationTimestamp;
			this.userService = userService;
			this.projectRepository = projectRepository;
		}
	
		
		public ProjectModel createProjectAttributes(CreateProjectCommand createProjectCommand) {
			UUID userId = createProjectCommand.getUserId();
			
				//UserModel userModel = userService.getUserById(userId)
					//	.orElseThrow(() -> new NoRegisteredUsersException("No registered users"));
				
			
			 Optional<UserModel> userModel = userService.getUserById(userId);
			
			 if(userModel.isPresent()) {
				 return new ProjectBuilder()
							.name(createProjectCommand.getName())
							.description(createProjectCommand.getDescription())
							.createdAt(generationTimestamp.timestampGenerator())
							.updatedAt(generationTimestamp.timestampGenerator())
							.startDate(createProjectCommand.getStartDate())
							.deadlineDate(createProjectCommand.getDeadlineDate())
							.priority(createProjectCommand.getPriority())
							.status(createProjectCommand.getStatus())
							.user(userModel.get())
							.build();
							
			 } else throw new NoRegisteredUsersException("No registered users");
				
		}
}
