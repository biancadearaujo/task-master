package br.com.project.api.taskmaster.model.project;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.project.api.taskmaster.model.user.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
@Table(name = "TB_PROJECTS")
public class ProjectModel  implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_project")
	private UUID id;
	
	@NotBlank(message = "Name cannot be null.")
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "deadline_date")
	private LocalDateTime deadlineDate;
	
	@Column(name = "priority")
	private String priority;// ver o tipo de prioridade.
	
	@Column(name = "status")
	private String status;
	
	//@Column(name = "creator_user_id")
	//private UUID creatorUserId;//id do usuario.
	
	/*@ManyToOne
	@JoinColumn(name="id_user")
	private UserModel user;
	*/
	
	@ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserModel user;
	
	public ProjectModel() {
	}
	
	//Sem relacionamento e creatorUserID
	public ProjectModel(UUID id, @NotBlank(message = "Name cannot be null.") String name, String description,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startDate, LocalDateTime deadlineDate,
			String priority, String status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDate = startDate;
		this.deadlineDate = deadlineDate;
		this.priority = priority;
		this.status = status;
	}

	//Sem relacionamento e creatorUserID
	public ProjectModel(@NotBlank(message = "Name cannot be null.") String name, String description,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startDate, LocalDateTime deadlineDate,
			String priority, String status) {
		super();
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDate = startDate;
		this.deadlineDate = deadlineDate;
		this.priority = priority;
		this.status = status;
	}
	
	public ProjectModel(@NotBlank(message = "Name cannot be null.") String name, String description,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startDate, LocalDateTime deadlineDate,
			String priority, String status, UserModel user) {
		super();
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDate = startDate;
		this.deadlineDate = deadlineDate;
		this.priority = priority;
		this.status = status;
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(LocalDateTime deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*public ProjectModel(UUID id, @NotBlank(message = "Name cannot be null.") String name, String description,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startDate, LocalDateTime deadlineDate,
			String priority, String status, UUID creatorUserId, UserModel user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDate = startDate;
		this.deadlineDate = deadlineDate;
		this.priority = priority;
		this.status = status;
		this.creatorUserId = creatorUserId;
		this.user = user;
	}
	
	

	public ProjectModel(@NotBlank(message = "Name cannot be null.") String name, String description,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startDate, LocalDateTime deadlineDate,
			String priority, String status, UUID creatorUserId, UserModel user) {
		super();
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDate = startDate;
		this.deadlineDate = deadlineDate;
		this.priority = priority;
		this.status = status;
		this.creatorUserId = creatorUserId;
		this.user = user;
	}
*/

	

}
