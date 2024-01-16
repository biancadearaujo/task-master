package br.com.project.api.taskmaster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.api.taskmaster.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
	public Optional<UserModel> findByLogin(String login);
}
