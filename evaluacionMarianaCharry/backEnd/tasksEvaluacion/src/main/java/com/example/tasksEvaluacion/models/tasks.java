package com.example.tasksEvaluacion.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class tasks {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column (name="id",nullable=false,length=36)
	private String id;
	
	@Column (name="title",nullable=false,length=36)
	private String title;
	
	@Column (name="due_date",nullable=false,length=36)
	private Date due_date;
	
	@Column (name="assigned_to",nullable=false,length=36)
	private String assigned_to;
	
	@Enumerated(EnumType.STRING)
	@Column (name="status",nullable=false,length=100)
	private status status;

	public tasks() {
		super();
	}

	public tasks(String id, String title, Date due_date, String assigned_to,
			com.example.tasksEvaluacion.models.status status) {
		super();
		this.id = id;
		this.title = title;
		this.due_date = due_date;
		this.assigned_to = assigned_to;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public String getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}

	public status getStatus() {
		return status;
	}

	public void setStatus(status status) {
		this.status = status;
	}

	public boolean contieneCamposVacios() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
