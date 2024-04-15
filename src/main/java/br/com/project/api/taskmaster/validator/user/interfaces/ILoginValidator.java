package br.com.project.api.taskmaster.validator.user.interfaces;

public interface ILoginValidator {

	boolean loginIsValid(String login);
	
	static final int MAXIMUM_SIZE = 30;
}