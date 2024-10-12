package br.com.project.api.taskmaster.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Autowired
	SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(authorize -> authorize
	        		.requestMatchers(HttpMethod.POST, "/auth/login").permitAll() //fazer login
	        		.requestMatchers(HttpMethod.POST, "/auth").permitAll() // cadastrar
	        		//.requestMatchers(HttpMethod.POST, "/auth/project").permitAll()
	        		.requestMatchers(HttpMethod.GET, "/auth").hasRole("ADMIN")
	        		.requestMatchers(HttpMethod.GET, "{userId}/auth").hasRole("ADMIN")
	        		//.requestMatchers(HttpMethod.GET, "/auth/project").hasRole("USER")
	        		.requestMatchers(HttpMethod.GET, "/auth/project").hasRole("USER")
	        		.requestMatchers(HttpMethod.POST, "/auth/project").hasRole("USER")
	        		.requestMatchers(HttpMethod.POST, "/auth/project").hasRole("USER")
	        		.requestMatchers(HttpMethod.GET, "/auth/project").hasRole("USER")
	        		//.requestMatchers(HttpMethod.PUT, "/auth/{userId}/update")//.hasRole("USER")
	        		//.requestMatchers(HttpMethod.DELETE, "/auth/{userId}").hasRole("USER")
	        		.requestMatchers(AUTH_WHITELIST).permitAll()
	        		.anyRequest().authenticated()
	        )
	        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	        .build();
	}
	
	private static final String[] AUTH_WHITELIST = {
			"/api/v1/auth/**",
			"/v3/api-docs/**",
			"/v3/api-docs.yaml",
			"/swagger-ui/**",
			"swagger-ui.html"
			
	};
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
