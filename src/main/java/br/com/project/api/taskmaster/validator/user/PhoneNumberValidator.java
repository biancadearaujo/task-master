package br.com.project.api.taskmaster.validator.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.validator.user.interfaces.IPhoneNumberValidator;

@Component
public class PhoneNumberValidator implements IPhoneNumberValidator{

	@Override
	public List<String> phoneNumberIsValid(String phoneNumber) {
		List<String> errors = new ArrayList<>();
		
		if(phoneNumber.matches(CELL_PATTERN_VALIDATION) || phoneNumber.matches(PHONE_PATTERN_VALIDATION)) {
		errors.add("The telephone number provided is not valid.");
	}
		return errors;
	}
}
