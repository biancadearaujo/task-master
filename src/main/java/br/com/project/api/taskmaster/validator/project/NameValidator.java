package br.com.project.api.taskmaster.validator.project;

import java.util.ArrayList;
import java.util.List;

import br.com.project.api.taskmaster.validator.project.interfaces.INameValidator;

public class NameValidator implements INameValidator{

	@Override
	public List<String> NameIsValid(String name) {

		List<String> errors = new ArrayList<>();
		
		if(name.length()>MAXIMUM_SIZE) {
			errors.add("The login is too big.");
		}
		
		return errors;
	}

}
