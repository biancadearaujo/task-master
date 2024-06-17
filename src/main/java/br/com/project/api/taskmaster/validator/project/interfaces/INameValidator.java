package br.com.project.api.taskmaster.validator.project.interfaces;

import java.util.List;

public interface INameValidator {

	List<String> nameIsValid(String name);
	static final int MAXIMUM_SIZE = 100;
	static final int MINIMUM_SIZE = 0;
}
