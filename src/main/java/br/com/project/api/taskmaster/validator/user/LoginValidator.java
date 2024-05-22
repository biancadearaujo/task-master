package br.com.project.api.taskmaster.validator.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.repository.user.UserRepository;
import br.com.project.api.taskmaster.validator.user.interfaces.ILoginValidator;

@Component
public class LoginValidator implements ILoginValidator{

	final UserRepository userRepository;
	
	public LoginValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public List<String> loginIsValid(String login) {
		List<String> errors = new ArrayList<>();
		
		if(userRepository.findByLogin(login) != null ) {
			errors.add("Login already registered.");
		}
		if(login.length() > MAXIMUM_SIZE) {
			errors.add("The login is too big.");
		}
		
		return errors;
	}
}
