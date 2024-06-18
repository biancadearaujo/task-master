package br.com.project.api.taskmaster.service.project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.project.api.taskmaster.command.project.CreateProjectCommand;
import br.com.project.api.taskmaster.exception.project.InvalidProjectException;
import br.com.project.api.taskmaster.exception.project.NoRegisteredProjectException;
import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.repository.project.ProjectRepository;
import br.com.project.api.taskmaster.validator.project.ProjectValidator;

@Service
public class ProjectService {

	final ProjectRepository projectRepository;
	final ProjectValidator projectValidator;
	final ProjectCreator projectCreator;

	public ProjectService(ProjectRepository projectRepository, ProjectValidator projectValidator, ProjectCreator projectCreator) {
		this.projectRepository = projectRepository;
		this.projectValidator = projectValidator;
		this.projectCreator = projectCreator;
	}
	
	
	public Page<ProjectModel> getAll(Pageable pageable) {
		return projectRepository.findAll(pageable);	
	}
	
	public Optional<ProjectModel> getById(UUID id) {
		Optional<ProjectModel> projectModel = Optional.of(projectRepository.findById(id).orElseThrow(() -> new NoRegisteredProjectException("Project not found")));
		return projectModel;	
	}
	
	public Optional<ProjectModel> getByName(String name) {
		Optional<ProjectModel> projectModel = Optional.of(projectRepository.findByName(name).orElseThrow(() -> new NoRegisteredProjectException("Project not found")));
		return projectModel;
	}
	
	public ProjectModel save(CreateProjectCommand createProjectCommand) {
		
		try {
			List<String> validationErrors = projectValidator.validateProject(createProjectCommand);
			
			if(!validationErrors.isEmpty()) {
				throw new InvalidProjectException(validationErrors);
			}
			
			ProjectModel newProject = projectCreator.createProjectAttributes(createProjectCommand);
			return projectRepository.save(newProject);
			
		} catch (InvalidProjectException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ProjectModel update() {
		return null;
	}
	
	public void delete() {
		
	}
	
}
