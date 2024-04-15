package br.com.project.api.taskmaster.validator.user;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.validator.user.interfaces.IPasswordValidator;

@Component
public class PasswordValidator implements IPasswordValidator{

	@Override
	public boolean passwordIsValid(String password) {
		if(password == null || password.length() < MINIMUM_SIZE || password.length() > MAXIMUM_SIZE) {
			return false;
		}
		return Pattern.matches(PATTERN_VALIDATION, password);
	}
	
}
