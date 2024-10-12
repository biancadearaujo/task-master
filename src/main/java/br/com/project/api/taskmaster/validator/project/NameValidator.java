package br.com.project.api.taskmaster.validator.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.validator.project.interfaces.INameValidator;

@Component
public class NameValidator implements INameValidator{

	@Override
	public List<String> nameIsValid(String name) {

		List<String> errors = new ArrayList<>();
		
		if(name == null || name.length()>MAXIMUM_SIZE || name.length()<MINIMUM_SIZE) {
			errors.add("The project name must be between " + MINIMUM_SIZE + " and " + MAXIMUM_SIZE + " characters long.");
		}
		
		return errors;
	}

}
