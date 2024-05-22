package br.com.project.api.taskmaster.validator.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.command.CreateUserCommand;
import br.com.project.api.taskmaster.validator.user.interfaces.IUserValidator;

@Component
public class UserValidator implements IUserValidator{

	final PasswordValidator passwordValidator;
	final PhoneNumberValidator phoneNumberValidator;
	final LoginValidator loginValidator;
	final EmailValidator emailValidator;
	
	public UserValidator(PasswordValidator passwordValidator, PhoneNumberValidator phoneNumberValidator,
			LoginValidator loginValidator, EmailValidator emailValidator) {
		this.passwordValidator = passwordValidator;
		this.phoneNumberValidator = phoneNumberValidator;
		this.loginValidator = loginValidator;
		this.emailValidator = emailValidator;
	}

	public List<String> validateUser(CreateUserCommand createUserCommand){
		List<String> errors = new ArrayList<>();
		
		errors.addAll(emailValidator.emailIsValid(createUserCommand.getEmail()));
		errors.addAll(loginValidator.loginIsValid(createUserCommand.getLogin()));
		errors.addAll(passwordValidator.passwordIsValid(createUserCommand.getPassword()));
		errors.addAll(phoneNumberValidator.phoneNumberIsValid(createUserCommand.getPhoneNumber()));
		
		return errors;
	}
	
	public List<String> validateUserUpdate(UUID userId, Map<String, Object> updateAttributes){
		List<String> errors = new ArrayList<>();
		
		if (updateAttributes.containsKey("email")) {
	        errors.addAll(emailValidator.emailIsValid((String) updateAttributes.get("email")));
	    }

	    if (updateAttributes.containsKey("login")) {
	        errors.addAll(loginValidator.loginIsValid((String) updateAttributes.get("login")));
	    }

	    if (updateAttributes.containsKey("password")) {
	        errors.addAll(passwordValidator.passwordIsValid((String) updateAttributes.get("password")));
	    }

	    if (updateAttributes.containsKey("phoneNumber")) {
	        errors.addAll(phoneNumberValidator.phoneNumberIsValid((String) updateAttributes.get("phoneNumber")));
	    }
		return errors;
	}
}
