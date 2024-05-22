package br.com.project.api.taskmaster.validator.user.interfaces;

import java.util.List;

public interface IEmailValidator {

	List<String> emailIsValid(String email);
}
