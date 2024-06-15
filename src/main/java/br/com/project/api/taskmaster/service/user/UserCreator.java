package br.com.project.api.taskmaster.service.user;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.builder.user.UserBuilder;
import br.com.project.api.taskmaster.command.user.CreateUserCommand;
import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.utils.user.EncryptionPasswordEncoder;
import br.com.project.api.taskmaster.utils.user.GenerationTimestamp;

@Component
public class UserCreator {
	
	private final EncryptionPasswordEncoder encryptionPasswordEncoder;
	private final GenerationTimestamp generationTimestamp;
	
	public UserCreator(EncryptionPasswordEncoder encryptionPasswordEncoder, GenerationTimestamp generationTimestamp) {
		this.encryptionPasswordEncoder = encryptionPasswordEncoder;
		this.generationTimestamp = generationTimestamp;
	}


	public UserModel createUserAttributes(CreateUserCommand createUserCommand) {

		String encryptedPassword = encryptionPasswordEncoder.passwordEncode(createUserCommand.getPassword());
        
        return new UserBuilder()
        		.name(createUserCommand.getName())
        		.email(createUserCommand.getEmail())
        		.login(createUserCommand.getLogin())
        		.password(encryptedPassword)
        		.role(createUserCommand.getRole().USER)
        		.createdAt(generationTimestamp.timestampGenerator())
        		.updatedAt(generationTimestamp.timestampGenerator())
        		.phoneNumber(createUserCommand.getPhoneNumber())
        		.avatarUrl(createUserCommand.getAvatarUrl())
        		.isActive(true)
        		.verificationToken(createUserCommand.getVerificationToken())
        		.build();
	}
}
