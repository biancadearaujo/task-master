package br.com.project.api.taskmaster.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.api.taskmaster.command.CreateUserCommand;
import br.com.project.api.taskmaster.exception.user.EmailAlreadyExistsException;
import br.com.project.api.taskmaster.validator.user.EmailValidator;
import jakarta.validation.Valid;

@RestController
public class EmailController {

	final EmailValidator emailValidator;
	
	public EmailController(EmailValidator emailValidator) {
		this.emailValidator = emailValidator;
	}
	
	public ResponseEntity<String> validarEmail(CreateUserCommand createUserCommand){
		try {
			if(emailValidator.emailIsValid(createUserCommand.getEmail())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail already registered.");
		}
		} catch (EmailAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail already registered.");
		}
		return ResponseEntity.ok().build();
	}
}
