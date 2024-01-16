package br.com.project.api.taskmaster.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.project.api.taskmaster.data.UserDetailData;
import br.com.project.api.taskmaster.model.UserModel;

public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter{
	public static final int TOKEN_EXPIRATION = 600_000; //Token expiration time, 600_000 is equivalent to 10 minutes.
	public static final String TOKEN_SENHA = "bf32c796-df5d-4bab-92ac-d9a51e5b5b65";
	
	private final AuthenticationManager authenticationManager;

	public JWTAuthenticateFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserModel userModel = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userModel.getLogin(),
					userModel.getPassword(),
					new ArrayList<>()
					));
			
		} catch (IOException e) {
			throw new RuntimeException("Falha ao autenticar usuário", e);
		}	
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailData userDetailData = (UserDetailData) authResult.getPrincipal();
		
		String token = JWT.create()
				.withSubject(userDetailData.getUsername()) //Username.
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION)) //Token expiration date and time =  current milliseconds plus token expiration.
				.sign(Algorithm.HMAC512(TOKEN_SENHA));  //Sign the token.
		
		response.getWriter().write(token); //Register the token in the page body.
		response.getWriter().flush();
	}
}
