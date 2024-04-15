package br.com.project.api.taskmaster.model.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUser")

@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable, UserDetails{
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user")
	private UUID id;
	
	@NotBlank(message = "Name cannot be null.")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotBlank(message = "Name cannot be null.")
	@Column(name = "email", unique = true)
	private String email;
	
	@NotBlank(message = "Login cannot be null.")
	@Column(name = "login", nullable = false, unique = true)
	private String login;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Attribute to not return the user's password.
	@NotBlank(message = "Password cannot be null.")
	@Column(name = "password", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRole role;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;//
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "avatar_url")
	private String avatarUrl;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "verification_token")
	private String verificationToken;
	
	
	public UserModel() {
	}

	
	public UserModel(String name, String email, String login, String password, UserRole role, LocalDateTime createdAt,
			LocalDateTime updatedAt, String phoneNumber, String avatarUrl, boolean isActive, String verificationToken) {
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.phoneNumber = phoneNumber;
		this.avatarUrl = avatarUrl;
		this.isActive = isActive;
		this.verificationToken = verificationToken;
	}

	
	public UserModel(UUID id, String name, String email, String login, String password, UserRole role,
			LocalDateTime createdAt, LocalDateTime updatedAt, String phoneNumber, String avatarUrl, boolean isActive,
			String verificationToken) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.phoneNumber = phoneNumber;
		this.avatarUrl = avatarUrl;
		this.isActive = isActive;
		this.verificationToken = verificationToken;
	}


	public UUID getIdUser() {
		return id;
	}

	public void setIdUser(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}
	
/*@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}*/
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
