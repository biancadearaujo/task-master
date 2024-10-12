package br.com.project.api.taskmaster.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.project.api.taskmaster.model.user.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

	UserDetails findByLogin(String login);
	//Optional<UserDetails> findByLogin(String login);
	//Optional<UserModel> findByLogin(String login);
	Optional<UserModel> findByEmail(String email);
}
