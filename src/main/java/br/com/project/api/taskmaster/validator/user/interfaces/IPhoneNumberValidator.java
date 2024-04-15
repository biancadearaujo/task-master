package br.com.project.api.taskmaster.validator.user.interfaces;

public interface IPhoneNumberValidator {

	boolean phoneNumberIsValid(String phoneNumber);
	
	static final String PHONE_PATTERN_VALIDATION = "^(\\d{10}|\\d{2} \\d{4}-\\d{4}|\\(\\d{2}\\) \\d{4}-\\d{4})$";
	static final String CELL_PATTERN_VALIDATION = "^(\\d{11}|\\d{2} \\d{5}-\\d{4}|\\(\\d{2}\\) \\d{5}-\\d{4})$";
	
}
