package br.com.project.api.taskmaster.service.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.project.api.taskmaster.command.user.CreateUserCommand;
import br.com.project.api.taskmaster.exception.user.InvalidUserException;
import br.com.project.api.taskmaster.exception.user.NoRegisteredUsersException;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.repository.user.UserRepository;
import br.com.project.api.taskmaster.utils.user.GenerationTimestamp;
import br.com.project.api.taskmaster.validator.user.UserValidator;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	final UserRepository userRepository;
	final UserCreator userCreator;
	final UserUpdater userUpdater;
	final UserValidator userValidator;
	
	public UserService(UserRepository userRepository, UserCreator userCreator, UserUpdater userUpdater,
			GenerationTimestamp generationTimestamp, UserValidator userValidator) {
		this.userRepository = userRepository;
		this.userCreator = userCreator;
		this.userUpdater = userUpdater;
		this.userValidator = userValidator;
	}


	public Page<UserModel> getAllUsers(Pageable pageable) {
    	
    	return userRepository.findAll(pageable);	
    }
	
	public Optional<UserModel> getUserById(UUID userId) {
			Optional<UserModel> userModel = Optional.of(userRepository.findById(userId).orElseThrow(() -> new NoRegisteredUsersException("User not found")));
			
			return userModel;
	}
    

    @Transactional
	public UserModel register(CreateUserCommand createUserCommand){
		
    	try {
    		List<String> validationErrors = userValidator.validateUser(createUserCommand);
    		
    		if(!validationErrors.isEmpty()) {
    			throw new InvalidUserException(validationErrors);
    		}
    		
    		UserModel newUser = userCreator.createUserAttributes(createUserCommand);
    		return userRepository.save(newUser);
			
		} catch (InvalidUserException e) {
			logger.error("Validation error for the registering user", e);
			throw e;
		}catch (Exception e) {
			logger.error("Error registering user", e);
	        throw new RuntimeException("Error registering user", e);
		}   
	}
	
    
    public Optional<UserModel> updateUser(UUID userId, Map<String, Object> updateAttributes) {
        List<String> validationErrors = userValidator.validateUserUpdate(userId, updateAttributes);

        if (!validationErrors.isEmpty()) {
            throw new InvalidUserException(validationErrors);
        }

        return userRepository.findById(userId)
            .map(userModel -> {
                userUpdater.updateUserAttributes(updateAttributes, userModel);
                return userRepository.save(userModel);
            });
    }
    
	
    @Transactional
	public String delete(UUID id) {
    	
		Optional<UserModel> userModel = userRepository.findById(id);
		
		if(userModel.isEmpty())
			throw new NoRegisteredUsersException("No registered users");
		userRepository.deleteById(id);
		
		return"User successfully deleted";
		
	}	
}
