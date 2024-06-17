package br.com.project.api.taskmaster.exception.project;

import java.util.List;

public class InvalidProjectException extends RuntimeException{

	public InvalidProjectException(List<String> validationErrors) {
		super();
	}
}
