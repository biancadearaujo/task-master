package br.com.project.api.taskmaster.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.project.api.taskmaster.model.user.UserModel;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	public String login(String login, String password) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(login, password);
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((UserModel)auth.getPrincipal());
		
		return token;
	}
	
}
