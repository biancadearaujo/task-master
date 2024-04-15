package br.com.project.api.taskmaster.validator.user;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.validator.user.interfaces.IPhoneNumberValidator;

@Component
public class PhoneNumberValidator implements IPhoneNumberValidator{

	@Override
	public boolean phoneNumberIsValid(String phoneNumber) {
		if(phoneNumber.matches(CELL_PATTERN_VALIDATION) || phoneNumber.matches(PHONE_PATTERN_VALIDATION)) {
		return true;
	}
		return false;
	}
}
