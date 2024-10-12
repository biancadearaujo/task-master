package br.com.project.api.taskmaster.controller.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

import br.com.project.api.taskmaster.command.user.CreateUserCommand;
import br.com.project.api.taskmaster.exception.user.InvalidUserException;
import br.com.project.api.taskmaster.exception.user.NoRegisteredUsersException;
import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.service.project.ProjectService;
import br.com.project.api.taskmaster.service.user.UserService;
import br.com.project.api.taskmaster.validator.user.UserValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	final UserService userService;
	final UserValidator userValidator;
	final ProjectService projectService;
	
	public UserController(UserService userService, UserValidator userValidator, ProjectService projectService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.projectService = projectService;
	}


	@GetMapping
	//@PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.idUser")
	public ResponseEntity<Page<UserModel>> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		
		Page<UserModel> users = userService.getAllUsers(pageable);
		
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);		
	}

	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable UUID userId){
		try {
			Optional<UserModel> user = userService.getUserById(userId);
			return ResponseEntity.status(HttpStatus.OK).body(user);
			} catch (NoRegisteredUsersException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
	        }
	}
	
	
	@PostMapping
	public ResponseEntity<Object> register(@RequestBody @Valid CreateUserCommand createUserCommand) {
	    try {
	        List<String> validationErrors = userValidator.validateUser(createUserCommand);

	        if (!validationErrors.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(validationErrors);
	        }

	        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(createUserCommand));
	    } catch (Exception e) {
	    	logger.error("Error registering user", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
	    }
	}
	
	
	@PutMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.idUser")
    public ResponseEntity<Object> updateUser(@PathVariable UUID userId, @RequestBody @Valid Map<String, Object> updateAttributes) {
       
		try {
            List<String> validationErrors = userValidator.validateUserUpdate(userId, updateAttributes);

            if (!validationErrors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(validationErrors);
            }

            Optional<UserModel> updatedUser = userService.updateUser(userId, updateAttributes);
            
            if (updatedUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

        } catch (InvalidUserException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        } catch (Exception e) {
            logger.error("Unexpected error updating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error updating user");
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
