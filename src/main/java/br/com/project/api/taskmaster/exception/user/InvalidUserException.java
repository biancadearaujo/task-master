package br.com.project.api.taskmaster.exception.user;

import java.util.List;

public class InvalidUserException extends RuntimeException{

	public InvalidUserException(List<String> validationErrors) {
        super();
    }

}
