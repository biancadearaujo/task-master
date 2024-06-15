package br.com.project.api.taskmaster.exception.project;

public class NoRegisteredProjectException extends RuntimeException{

	public NoRegisteredProjectException(String message) {
		super(message);
	}
}
