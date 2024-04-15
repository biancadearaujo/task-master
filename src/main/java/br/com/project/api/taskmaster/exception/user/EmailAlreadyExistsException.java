package br.com.project.api.taskmaster.exception.user;

import org.springframework.http.ResponseEntity;

public class EmailAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private ResponseEntity<String> responseEntity;

	public EmailAlreadyExistsException(ResponseEntity<String> responseEntity) {
		super("Email already registered.");
		this.responseEntity = responseEntity;
	}

	public ResponseEntity<String> getResponseEntity() {
		return responseEntity;
	}

}