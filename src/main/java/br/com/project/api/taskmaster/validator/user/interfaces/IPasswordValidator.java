package br.com.project.api.taskmaster.validator.user.interfaces;

public interface IPasswordValidator {

	boolean passwordIsValid(String password);
	
	static final int MINIMUM_SIZE = 8;
	static final int MAXIMUM_SIZE = 16;
    static final String PATTERN_VALIDATION = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[\\w@#$%^*&!?]{8,16}$";

}
