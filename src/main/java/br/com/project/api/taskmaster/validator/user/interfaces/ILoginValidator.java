package br.com.project.api.taskmaster.validator.user.interfaces;

import java.util.List;

public interface ILoginValidator {

	List<String> loginIsValid(String login);
	
	static final int MAXIMUM_SIZE = 30;
}