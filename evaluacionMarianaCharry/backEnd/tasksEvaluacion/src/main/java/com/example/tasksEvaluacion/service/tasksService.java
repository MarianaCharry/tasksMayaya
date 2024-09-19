package com.example.tasksEvaluacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tasksEvaluacion.interfaceService.ITasksService;
import com.example.tasksEvaluacion.interfaces.ITasks;
import com.example.tasksEvaluacion.models.tasks;

@Service
public class tasksService implements ITasksService {

	@Autowired
	private ITasks data;
	
	@Override
	public String save(tasks tasks) {
		data.save(tasks);
		return tasks.getId();
	}
	
	@Override
	public List<tasks>findAll(){
		List<tasks>listaTasks=(List<tasks>) data.findAll();
		
		return listaTasks;
	}
	
	@Override
	public List<tasks>filtroTasks(String filtro){
		List<tasks>listaTasks=data.filtroTasks(filtro);
		return listaTasks;
	}
	
	@Override
	public Optional<tasks>findOne(String id){
		Optional<tasks>tasks=data.findById(id);
		return tasks;
	}
	
	@Override
	public int delete(String id) {
		data.deleteById(id);
		return 1;
	}
	
	@Override
	public List<tasks>filtroIngresoTasks(String title){
		List <tasks>listaTasks=data.filtroIngresoTasks(title);
		return listaTasks;
	}
	
}
