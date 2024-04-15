package br.com.project.api.taskmaster.dto.user;

import java.time.LocalDateTime;

import br.com.project.api.taskmaster.model.user.UserRole;

public record RegisterDTO(
		String name, 
		String email, 
		String login, 
		String password, 
		UserRole role,
		LocalDateTime createdAt, 
		LocalDateTime updatedAt, 
		String phoneNumber, 
		String avatarUrl, 
		boolean isActive,
		String verificationToken, 
		String privider
		) {
	
}
