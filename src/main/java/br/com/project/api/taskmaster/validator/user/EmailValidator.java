package br.com.project.api.taskmaster.validator.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.exception.user.EmailAlreadyExistsException;
import br.com.project.api.taskmaster.exception.user.UserAlreadyExistsException;
import br.com.project.api.taskmaster.repository.user.UserRepository;
import br.com.project.api.taskmaster.validator.user.interfaces.IEmailValidator;

@Component
public class EmailValidator implements IEmailValidator{

	final UserRepository userRepository;
	
	public EmailValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public boolean emailIsValid(String email) {
		if(userRepository.findByEmail(email).isEmpty()) {
			return false;
		}
		//throw new UserAlreadyExistsException("usuario ja existe!");
		return true;
	}

	
}