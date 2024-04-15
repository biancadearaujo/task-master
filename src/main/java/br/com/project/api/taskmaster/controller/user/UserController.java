package br.com.project.api.taskmaster.controller.user;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.api.taskmaster.command.CreateUserCommand;
import br.com.project.api.taskmaster.exception.user.NoRegisteredUsersException;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.service.user.UserService;
import br.com.project.api.taskmaster.validator.user.EmailValidator;
import br.com.project.api.taskmaster.validator.user.LoginValidator;
import br.com.project.api.taskmaster.validator.user.PasswordValidator;
import br.com.project.api.taskmaster.validator.user.PhoneNumberValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	final UserService userService;
	final PasswordValidator passwordValidator;
	final PhoneNumberValidator phoneNumberValidator;
	final LoginValidator loginValidator;
	final EmailValidator emailValidator;
	final EmailController emailController;
	
	public UserController(UserService userService, PasswordValidator passwordValidator,
			PhoneNumberValidator phoneNumberValidator, LoginValidator loginValidator, EmailValidator emailValidator,
			EmailController emailController) {
		this.userService = userService;
		this.passwordValidator = passwordValidator;
		this.phoneNumberValidator = phoneNumberValidator;
		this.loginValidator = loginValidator;
		this.emailValidator = emailValidator;
		this.emailController = emailController;
	}


	@GetMapping
	public ResponseEntity<Page<UserModel>> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		
		Page<UserModel> users = userService.getAllUsers(pageable);
		
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);
		
	}
	
	
	@PostMapping
	public ResponseEntity<Object> register(@RequestBody @Valid CreateUserCommand createUserCommand){
		
		try {
			if(!passwordValidator.passwordIsValid(createUserCommand.getPassword())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("The password entered is not valid.");
			}
			if(!phoneNumberValidator.phoneNumberIsValid(createUserCommand.getPhoneNumber())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("The telephone number provided is not valid.");
			}
			if(!loginValidator.loginIsValid(createUserCommand.getLogin())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("The Login entered is not valid.");
			}
			if((emailController.validarEmail(createUserCommand)).getStatusCode().is2xxSuccessful()) {
				//return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(createUserCommand));
			}
			//emailController.validarEmail(createUserCommand);
			//emailValidator.emailIsValid(createUserCommand.getEmail());
			/*if(!emailValidator.emailIsValid(createUserCommand.getEmail())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail already registered.");
			}*/
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(createUserCommand));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error register user");
		}
		
	}
	
	
	/*
	 *  ResponseEntity<Object> response = emailController.validarEmail(createUserCommand);

        // Se a validação for bem-sucedida, cria o usuário
        if (response.getStatusCode().is2xxSuccessful()) {
            // ... código para criar o usuário ...
        }

        return response;
	 * */
	
	
	
	@PutMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.idUser")
    public ResponseEntity<Object> updateUser(@PathVariable UUID userId, @RequestBody @Valid Map<String, Object> updateAttributes) {
       
		try {
        	if(updateAttributes.containsKey("password") && !passwordValidator.passwordIsValid((String) updateAttributes.get("password"))) {
    			return ResponseEntity.status(HttpStatus.CONFLICT).body("The password entered is not valid.");
    		}
    		if(updateAttributes.containsKey("phoneNumber") && !phoneNumberValidator.phoneNumberIsValid((String)updateAttributes.get("phoneNumber"))) {
    			return ResponseEntity.status(HttpStatus.CONFLICT).body("The telephone number provided is not valid.");
    		}
    		if(updateAttributes.containsKey("login") && !loginValidator.loginIsValid((String) updateAttributes.get("login"))) {
    			return ResponseEntity.status(HttpStatus.CONFLICT).body("The Login entered is not valid.");
    		}
    		if(updateAttributes.containsKey("email") && !emailValidator.emailIsValid((String) updateAttributes.get("email"))) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail already registered.");
			}
    		
            userService.updateUser(userId, updateAttributes);
            return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
            
        } catch (NoRegisteredUsersException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
		
    }
	
	
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.idUser")
	public ResponseEntity<String> delete(@PathVariable UUID userId){
		try {
            userService.delete(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (NoRegisteredUsersException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
		
	}
	
}
