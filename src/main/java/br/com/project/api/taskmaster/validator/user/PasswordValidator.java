package br.com.project.api.taskmaster.validator.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.validator.user.interfaces.IPasswordValidator;

@Component
public class PasswordValidator implements IPasswordValidator{

	@Override
	public List<String> passwordIsValid(String password) {
		List<String> errors = new ArrayList<>();
		
		if(password == null || password.length() < MINIMUM_SIZE || password.length() > MAXIMUM_SIZE) {
			errors.add("The password entered is not valid.");
		}
		return errors;
	}
	
}
