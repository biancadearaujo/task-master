package br.com.project.api.taskmaster.repository.project;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.api.taskmaster.model.project.ProjectModel;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, UUID>{

	Optional<ProjectModel>findByName(String name);
}
