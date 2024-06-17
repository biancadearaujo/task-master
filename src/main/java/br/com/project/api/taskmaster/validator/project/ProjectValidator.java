package br.com.project.api.taskmaster.validator.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.command.project.CreateProjectCommand;

@Component
public class ProjectValidator {
	
	final NameValidator nameValidator;

	public ProjectValidator(NameValidator nameValidator) {
		this.nameValidator = nameValidator;
	}
	

	public List<String> validateProject(CreateProjectCommand createProjectCommand){
		List<String> errors = new ArrayList<>();
		
		errors.addAll(nameValidator.nameIsValid(createProjectCommand.getName()));
		
		return errors;
	}
}
