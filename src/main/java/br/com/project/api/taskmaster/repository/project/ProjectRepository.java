package br.com.project.api.taskmaster.repository.project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.api.taskmaster.model.project.ProjectModel;
import br.com.project.api.taskmaster.model.user.UserModel;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, UUID>{

	Optional<ProjectModel>findByName(String name);
	List<ProjectModel> findByUser(UserModel userModel);
}
