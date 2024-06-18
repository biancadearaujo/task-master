package br.com.project.api.taskmaster.validator.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.repository.user.UserRepository;
import br.com.project.api.taskmaster.validator.user.interfaces.IEmailValidator;

@Component
public class EmailValidator implements IEmailValidator{

	final UserRepository userRepository;
	
	public EmailValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public List<String> emailIsValid(String email) {
		List<String> errors = new ArrayList<>();
		
		if(userRepository.findByEmail(email).isPresent()) {
			errors.add("E-mail already registered.");
		}
		
		return errors;
	}	
}
