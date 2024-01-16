package br.com.project.api.taskmaster.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.data.UserDetailData;
import br.com.project.api.taskmaster.model.UserModel;
import br.com.project.api.taskmaster.repository.UserRepository;

@Component
public class DetailUserImplementedService implements UserDetailsService{

	private final UserRepository userRepository;
	
	public DetailUserImplementedService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserModel> userModel = userRepository.findByLogin(username);
		if(userModel.isEmpty()) {
			throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
		}
				
		return new UserDetailData(userModel);
	}

}
