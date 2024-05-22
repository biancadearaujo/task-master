package br.com.project.api.taskmaster.validator.user;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ValidationResult {

	private boolean isValid;
    private List<String> errorMessages;
    
    public boolean isValid() {
        return isValid;
    }

    public List<String> getValidationErrors() {
        return errorMessages;
    }
}
