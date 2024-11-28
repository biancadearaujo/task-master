package br.com.project.api.taskmaster.builder.user;

import java.time.LocalDateTime;
import java.util.List;

import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.model.user.UserRole;

public class UserBuilder {

	private String name;
	private String email;
	private String login;
	private String password;
	private UserRole role;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String phoneNumber;
	private String avatarUrl;
	private boolean isActive;
	private String verificationToken;
	private List<ProjectModel> projects;
	
	public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder login(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder role(UserRole role) {
        this.role = role;
        return this;
    }

    public UserBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public UserBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder avatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }
    
    public UserBuilder isActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public UserBuilder verificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
        return this;
    }
    
    public UserBuilder projects(List<ProjectModel> projects) {
        this.projects = projects;
        return this;
    }

    public UserModel build() {
        return new UserModel(
            this.name,
            this.email,
            this.login,
            this.password,
            this.role,
            this.createdAt,
            this.updatedAt,
            this.phoneNumber,
            this.avatarUrl,
            this.isActive,
            this.verificationToken,
            this.projects
        );
    }
	
}
