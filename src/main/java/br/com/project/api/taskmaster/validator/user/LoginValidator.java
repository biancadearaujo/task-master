package br.com.project.api.taskmaster.validator.user;

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
	public boolean loginIsValid(String login) {
		if(userRepository.findByLogin(login) == null) {
			return true;
		}
		if(login.length() > MAXIMUM_SIZE) {
			return true;
		}
		return false;
	}

}
