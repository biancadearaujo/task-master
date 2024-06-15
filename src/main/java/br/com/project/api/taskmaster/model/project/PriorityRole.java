package br.com.project.api.taskmaster.model.project;

public enum PriorityRole {

	lOW("lOW"),
	MEDIUM("MEDIUM"),
	HIGH("HIGH");
	
	private final String role;
	
	
	private PriorityRole(String role) {
		this.role = role;
	}

	
	public String getRole() {
		return role;
	}

}
