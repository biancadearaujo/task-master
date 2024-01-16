package br.com.project.api.taskmaster.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.api.taskmaster.command.CreateUserCommand;
import br.com.project.api.taskmaster.model.UserModel;
import br.com.project.api.taskmaster.repository.UserRepository;
import br.com.project.api.taskmaster.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	UserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<List<UserModel>>getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PostMapping
	public ResponseEntity<UserModel>createUser(@Valid @RequestBody CreateUserCommand createUserCommand){
		return new ResponseEntity<>(userService.createUser(createUserCommand),
				HttpStatus.CREATED);
	}
	
	//Intercept the error message.
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fielName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			
			errors.put(fielName, errorMessage);
		});
		
		return errors;
	}

}
