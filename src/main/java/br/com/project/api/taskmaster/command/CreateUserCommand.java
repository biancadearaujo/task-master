package br.com.project.api.taskmaster.command;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Valid
public class CreateUserCommand {
	@NotBlank(message = "Campo não informado")
	private String name;
	
	@NotBlank(message = "Campo não informado")
	@Email
	private String email;
	
	@NotBlank(message = "Campo não informado")
	private String login;
	
	@NotBlank(message = "Campo não informado")
	private String password;

	public CreateUserCommand(@NotBlank(message = "Campo não informado") String name,
			@NotBlank(message = "Campo não informado") @Email String email,
			@NotBlank(message = "Campo não informado") String login,
			@NotBlank(message = "Campo não informado") String password) {
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
