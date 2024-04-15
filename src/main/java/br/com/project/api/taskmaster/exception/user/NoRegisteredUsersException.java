package br.com.project.api.taskmaster.exception.user;

public class NoRegisteredUsersException extends RuntimeException{

	public NoRegisteredUsersException(String message) {
        super(message);
    }
}
