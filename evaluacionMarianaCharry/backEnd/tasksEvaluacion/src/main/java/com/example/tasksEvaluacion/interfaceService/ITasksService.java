package com.example.tasksEvaluacion.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.tasksEvaluacion.models.tasks;

public interface ITasksService {

	public String save (tasks tasks);
	public List<tasks>findAll();
	public List<tasks>filtroTasks(String filtro );
	public Optional<tasks>findOne(String id);
	public int delete (String id);
	public List<tasks>filtroIngresoTasks(String title);
}
