package br.com.project.api.taskmaster.service.user;

import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.project.api.taskmaster.model.user.UserModel;
import br.com.project.api.taskmaster.utils.user.GenerationTimestamp;
import br.com.project.api.taskmaster.utils.user.EncryptionPasswordEncoder;

@Component
public class UserUpdater {

	private final EncryptionPasswordEncoder encryptionPasswordEncoder;
	private final GenerationTimestamp generationTimestamp;
	

	public UserUpdater(EncryptionPasswordEncoder encryptionPasswordEncoder, GenerationTimestamp generationTimestamp) {
		this.encryptionPasswordEncoder = encryptionPasswordEncoder;
		this.generationTimestamp = generationTimestamp;
	}

	
	public void updateUserAttributes(Map<String, Object> updateAttributes, UserModel userModel) {
		for (Map.Entry<String, Object> entry : updateAttributes.entrySet()) {
	        String attributeName = entry.getKey();
	        Object attributeValue = entry.getValue();
	        
	        if (attributeValue != null) {
	           
	            try {
	                
	                String setterMethodName = "set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
	                
	                switch (attributeName) {
	                    case "id":  
	                        break;
	                    case "name":
	                        userModel.setName((String) attributeValue);
	                        break;
	                    case "email":
	                        userModel.setEmail((String) attributeValue);
	                        break;
	                    case "login":
	                        userModel.setLogin((String) attributeValue);
	                        break;
	                    case "password":
	                        userModel.setPassword((String) attributeValue);
	                        break;
	                    case "phoneNumber":
	                        userModel.setPhoneNumber((String) attributeValue);
	                        break;
	                    case "avatarUrl":
	                        userModel.setAvatarUrl((String) attributeValue);
	                        break;
	                    
	                    default:
	                        userModel.getClass().getMethod(setterMethodName, attributeValue.getClass()).invoke(userModel, attributeValue);
	                        break;
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    updatePasswordIfPresent(updateAttributes, userModel);
	    generationTimestamp.timestampGenerator();
	    
	}
	
	public void updatePasswordIfPresent(Map<String, Object> updateAttributes, UserModel userModel) {
		
		if (updateAttributes.containsKey("password")) {
	        String encryptedPassword = encryptionPasswordEncoder.passwordEncode((String) updateAttributes.get("password"));
	        userModel.setPassword(encryptedPassword);
	    }
	}
	
}
