package br.com.project.api.taskmaster.utils.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionPasswordEncoder {

	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public String passwordEncode(String password) {
		return encoder.encode(password);
	}
}
