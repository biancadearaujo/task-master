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
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.repository.project.ProjectRepository;
import br.com.project.api.taskmaster.repository.user.UserRepository;
import br.com.project.api.taskmaster.validator.project.ProjectValidator;

@Service
public class ProjectService {

	final ProjectRepository projectRepository;
	final ProjectValidator projectValidator;
	final ProjectCreator projectCreator;
	final UserRepository userRepository;

	public ProjectService(ProjectRepository projectRepository, ProjectValidator projectValidator, ProjectCreator projectCreator, UserRepository userRepository) {
		this.projectRepository = projectRepository;
		this.projectValidator = projectValidator;
		this.projectCreator = projectCreator;
		this.userRepository = userRepository;
	}
	
	
	public ProjectModel criarProjeto(ProjectModel projectModel) {
        return projectRepository.save(projectModel);
    }

    public List<ProjectModel> listarProjetosPorUsuario(UserModel user) {
        return projectRepository.findByUser(user);
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
	
	/*
	public ProjectModel save(CreateProjectCommand createProjectCommand) {
		
		try {
			List<String> validationErrors = projectValidator.validateProject(createProjectCommand);
			
			if(!validationErrors.isEmpty()) {
				throw new InvalidProjectException(validationErrors);
			}
			
			ProjectModel newProject = projectCreator.createProjectAttributes(createProjectCommand);
			
			if (newProject.getUser() == null) {
				System.out.println("Usuário recebeu nulo");
	            throw new RuntimeException("UserModel is null before saving the project.");
	        } else {
	            System.out.println("UserModel: " + newProject.getUser().getId());
	        }
			
			return projectRepository.save(newProject);
			
		} catch (InvalidProjectException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}*/
	/*
	public ProjectModel save(CreateProjectCommand createProjectCommand) {
			
			try {
				List<String> validationErrors = projectValidator.validateProject(createProjectCommand);
				
				if(!validationErrors.isEmpty()) {
					throw new InvalidProjectException(validationErrors);
				}
				
				ProjectModel newProject = projectCreator.createProjectAttributes(createProjectCommand);

		        // Obtém o usuário a partir do ID fornecido
		        UserModel user = userRepository.findById(createProjectCommand.getUserId())
		        		.orElseThrow();
		        
		        newProject.setUser(user);
				
				return projectRepository.save(newProject);
				
			} catch (InvalidProjectException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			}
	}*/
	
	public ProjectModel save(CreateProjectCommand createProjectCommand) {
			
			try {
				List<String> validationErrors = projectValidator.validateProject(createProjectCommand);
				
				if(!validationErrors.isEmpty()) {
					throw new InvalidProjectException(validationErrors);
				}
				
				UserModel userModel = userRepository.findById(createProjectCommand.getUserId())
						.orElseThrow(() -> new NoRegisteredProjectException("User not found"));
				
				
				ProjectModel newProject = projectCreator.createProjectAttributes(createProjectCommand);
								
				newProject.setUser(userModel);
				
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
