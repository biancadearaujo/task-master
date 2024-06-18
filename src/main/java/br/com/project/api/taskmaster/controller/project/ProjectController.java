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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.api.taskmaster.exception.project.NoRegisteredProjectException;
import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.service.project.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
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
	
	public void getByName() {
		
	}
	
	public void save() {
		
	}
	
	
}
