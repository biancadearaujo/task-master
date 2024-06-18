package br.com.project.api.taskmaster.model.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTask")

//@Entity
//@Table(name = "TB_TASKS")
public class TaskModel implements Serializable{
	/*private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_task")
	private UUID id;
	
	private UUID userId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "priority")
	private String priority;
	
	@Column(name = "dueDate")
	private LocalDateTime dueDate;
	
	@Column(name = "createdAt")
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "deadline_date")
	private LocalDateTime deadlineDate;
	
	@Column(name = "effort_estimation")
	private String effortEstimation;
	*/
}
