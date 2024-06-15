package br.com.project.api.taskmaster.validator.user.interfaces;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.com.project.api.taskmaster.command.user.CreateUserCommand;

public interface IUserValidator {

	public List<String> validateUser(CreateUserCommand createUserCommand);
	public List<String> validateUserUpdate(UUID userId, Map<String, Object> updateAttributes);
}
