package br.com.project.api.taskmaster.model.user;

public enum UserRole {

	/*ADMIN("admin"),
	USER("user");
	
	private String role;

	UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}*/
	
	ADMIN("ADMIN"),
	USER("USER");
	
	private final String role;

	UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	

}
