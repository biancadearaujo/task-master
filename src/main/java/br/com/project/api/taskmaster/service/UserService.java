package br.com.project.api.taskmaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.project.api.taskmaster.command.CreateUserCommand;
import br.com.project.api.taskmaster.model.UserModel;
import br.com.project.api.taskmaster.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private final PasswordEncoder encoder;
	
	public UserService(PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	
	public List<UserModel> getAllUsers() {
		return userRepository.findAll();
	}
	
	public UserModel createUser(CreateUserCommand createUserCommand) {
		UserModel userModel = new UserModel();
		
		userModel.setName(createUserCommand.getName());
		userModel.setEmail(createUserCommand.getEmail());
		userModel.setLogin(createUserCommand.getLogin());
		userModel.setPassword(encoder.encode(createUserCommand.getPassword())); //encrypted password

        return userRepository.save(userModel);
	}
	
}
