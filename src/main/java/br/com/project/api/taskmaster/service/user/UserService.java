package br.com.project.api.taskmaster.service.user;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.project.api.taskmaster.command.CreateUserCommand;
import br.com.project.api.taskmaster.exception.user.NoRegisteredUsersException;
import br.com.project.api.taskmaster.exception.user.UserAlreadyExistsException;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.repository.user.UserRepository;
import br.com.project.api.taskmaster.utils.user.GenerationTimestamp;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	final UserRepository userRepository;
	final UserCreator userCreator;
	final UserUpdater userUpdater;
	final GenerationTimestamp generationTimestamp;
	
	public UserService(UserRepository userRepository, UserCreator userCreator, UserUpdater userUpdater,
			GenerationTimestamp generationTimestamp) {
		this.userRepository = userRepository;
		this.userCreator = userCreator;
		this.userUpdater = userUpdater;
		this.generationTimestamp = generationTimestamp;
	}

	//não esta buscando os usuarios, apenas esta mostrando os dados da paginação

	public Page<UserModel> getAllUsers(Pageable pageable) {
    	
    	return userRepository.findAll(pageable);
    	
    }
	
	public UserModel getUserById(int id) {
		return null;
	}
    

    @Transactional
	public UserModel register(CreateUserCommand createUserCommand){
		
      /*  if(this.userRepository.findByLogin(createUserCommand.getLogin()) != null) {
        	throw new UserAlreadyExistsException("Existing user with this login");
        }
        */
       UserModel newUser = userCreator.createUserAttributes(createUserCommand);
        
        return userRepository.save(newUser);
        
	}
	
	
    @Transactional
	public UserModel updateUser(UUID userId, Map<String, Object> updateAttributes) {
	    //Trocar por Optional
	    UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new NoRegisteredUsersException("User not found"));
	    
	    userUpdater.updateUserAttributes(updateAttributes, userModel);

	    return userRepository.save(userModel);
	    
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
