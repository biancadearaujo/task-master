package br.com.project.api.taskmaster.command;

import java.time.LocalDateTime;

import br.com.project.api.taskmaster.model.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserCommand {

	@NotBlank(message = "Name cannot be null.")
	private String name;
	
	@Email
	@NotBlank(message = "Email cannot be null.") //Email tem que ser unico.
	private String email;
	
	@NotBlank(message = "Login cannot be null.")
	private String login;
	
	@NotBlank(message = "Password cannot be null.")
	private String password;
	
	private UserRole role;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private String phoneNumber;
	
	private String avatarUrl;
	
	private boolean isActive;
	
	private String verificationToken;
	
	private String privider;
	

	public CreateUserCommand(@NotBlank String name, @NotBlank String email, @NotBlank String login,
			@NotBlank String password, UserRole role, LocalDateTime createdAt, LocalDateTime updatedAt,
			String phoneNumber, String avatarUrl, boolean isActive, String verificationToken, String privider) {
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.phoneNumber = phoneNumber;
		this.avatarUrl = avatarUrl;
		this.isActive = isActive;
		this.verificationToken = verificationToken;
		this.privider = privider;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	public String getPrivider() {
		return privider;
	}

	public void setPrivider(String privider) {
		this.privider = privider;
	}	
	
}
