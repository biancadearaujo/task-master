package br.com.project.api.taskmaster.controller.project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.api.taskmaster.command.project.CreateProjectCommand;
import br.com.project.api.taskmaster.exception.project.NoRegisteredProjectException;
import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.service.project.ProjectService;
import br.com.project.api.taskmaster.service.user.UserService;
import br.com.project.api.taskmaster.validator.project.ProjectValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/project")
public class ProjectController {

	final ProjectService projectService;
	final ProjectValidator projectValidator;

	public ProjectController(ProjectService projectService, ProjectValidator projectValidator) {
		this.projectService = projectService;
		this.projectValidator = projectValidator;
	}
	
	
	/*
	@PostMapping
    public ResponseEntity<ProjectModel> criarProjeto(@RequestBody ProjectModel projeto, @AuthenticationPrincipal UserModel user) {
        projeto.setUser(user);
        ProjectModel novoProjeto = projectService.criarProjeto(projeto);
        return ResponseEntity.ok(novoProjeto);
    }

    @GetMapping
    public ResponseEntity<List<ProjectModel>> listarProjetos(@AuthenticationPrincipal UserModel user) {
        List<ProjectModel> projetos = projectService.listarProjetosPorUsuario(user);
        return ResponseEntity.ok(projetos);
    }*/
	
	@GetMapping
	public ResponseEntity<Page<ProjectModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<ProjectModel> projects = projectService.getAll(pageable);
		
		if(projects.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(projects);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<Optional<ProjectModel>> getById(@PathVariable UUID projectId) {
		try {
			Optional<ProjectModel> project = projectService.getById(projectId);
			return ResponseEntity.status(HttpStatus.OK).body(project);
			
		} catch (NoRegisteredProjectException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	/*public ResponseEntity<Optional<ProjectModel>> getByName(String name) {
		try {
			Optional<ProjectModel> project = projectService.getByName(name);
			return ResponseEntity.status(HttpStatus.OK).body(project);
		} catch (NoRegisteredProjectException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}*/
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid CreateProjectCommand createProjectCommand) {
		try {
			List<String> validationErrors = projectValidator.validateProject(createProjectCommand);
			
			if(!validationErrors.isEmpty()) {
				System.out.println("Aqui está dando erro");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(validationErrors);
			}
			
			ProjectModel savedProject = projectService.save(createProjectCommand);
			
			if(savedProject.getUser() != null) {
				System.out.println("Usuário associado ao projeto: " + savedProject.getUser().getId());
			} else {
				System.out.println("Usuário não foi associado ao projeto.");
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
			
			
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o projeto.");
		}
	}
	/*
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid CreateProjectCommand createProjectCommand) {
	    System.out.println("Entrou no método save do Controller");
	    try {
	        List<String> validationErrors = projectValidator.validateProject(createProjectCommand);
	        
	        if (!validationErrors.isEmpty()) {
	            System.out.println("Aqui está dando erro - Validação Falhou: " + validationErrors);
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(validationErrors);
	        }
	        System.out.println("Validação passou - Chamando projectService.save");
	        ProjectModel savedProject = projectService.save(createProjectCommand);
	        System.out.println("Projecto salvo com sucesso: " + savedProject);
	        return ResponseEntity.status(HttpStatus.OK).body(savedProject);
	    } catch (Exception e) {
	        System.out.println("Terceiro que pode estar dando erro - Exceção: " + e.getMessage());
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	*/
	
}
