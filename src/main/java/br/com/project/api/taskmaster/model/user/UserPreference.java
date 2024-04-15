package br.com.project.api.taskmaster.model.user;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserPreference {

	private boolean receiveEmailNotifications;
	private boolean receiveSMSNotifications;
	private boolean receivePushNotifications;
	
	private boolean showProfileToPublic;
	private boolean showPostsToPublic;
	
	private String preferredLanguage;
	private String preferredTheme;
	private String preferredLocation;
	
	public UserPreference() {
        this.receiveEmailNotifications = true;
        this.receiveSMSNotifications = false;
        this.receivePushNotifications = true;

        this.showProfileToPublic = true;
        this.showPostsToPublic = false;

        this.preferredLanguage = "en-US";
        this.preferredTheme = "light";
        this.preferredLocation = null;
    }

	public boolean isReceiveEmailNotifications() {
		return receiveEmailNotifications;
	}

	public void setReceiveEmailNotifications(boolean receiveEmailNotifications) {
		this.receiveEmailNotifications = receiveEmailNotifications;
	}

	public boolean isReceiveSMSNotifications() {
		return receiveSMSNotifications;
	}

	public void setReceiveSMSNotifications(boolean receiveSMSNotifications) {
		this.receiveSMSNotifications = receiveSMSNotifications;
	}

	public boolean isReceivePushNotifications() {
		return receivePushNotifications;
	}

	public void setReceivePushNotifications(boolean receivePushNotifications) {
		this.receivePushNotifications = receivePushNotifications;
	}

	public boolean isShowProfileToPublic() {
		return showProfileToPublic;
	}

	public void setShowProfileToPublic(boolean showProfileToPublic) {
		this.showProfileToPublic = showProfileToPublic;
	}

	public boolean isShowPostsToPublic() {
		return showPostsToPublic;
	}

	public void setShowPostsToPublic(boolean showPostsToPublic) {
		this.showPostsToPublic = showPostsToPublic;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getPreferredTheme() {
		return preferredTheme;
	}

	public void setPreferredTheme(String preferredTheme) {
		this.preferredTheme = preferredTheme;
	}

	public String getPreferredLocation() {
		return preferredLocation;
	}

	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	
}
