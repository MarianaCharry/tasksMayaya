package com.example.tasksEvaluacion.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.tasksEvaluacion.models.tasks;

public interface ITasks extends CrudRepository<tasks,String>{

	@Query("SELECT t FROM tasks t WHERE "
			+"t.title LIKE %?1% OR "
			+"t.due_date=?1 OR "
			+"t.assigned_to LIKE %?1% OR "
			+"t.status LIKE %?1%")
	List<tasks>filtroTasks(String filtro);
	
	@Query("SELECT t FROM tasks t WHERE t.title=?1")
	List<tasks>filtroIngresoTasks(String title);
}
