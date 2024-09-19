package com.example.tasksEvaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca_mayaya.models.tasks;
import com.example.tasksEvaluacion.interfaceService.ITasksService;
import com.example.tasksEvaluacion.models.tasks;
import com.example.tasksEvaluacion.service.emailService;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin
public class tasksController {
	
	@Autowired 
	private ITasksService tasksService;
	
	@Autowired
	private emailService emailService;
	
	@PostMapping("/")
	public ResponseEntity<Object> save(@RequestBody tasks tasks) {
		    
	    List<tasks> task = tasksService.filtroIngresoTasks(tasks.getTitle());
		    if (!task.isEmpty()) {
		        return new ResponseEntity<>("Ya tiene un ingreso activo", HttpStatus.BAD_REQUEST);
		    }
		    if (tasks.getTitle().equals("")) {

	            return new ResponseEntity<>("El titulo es un campo obligatorio", HttpStatus.BAD_REQUEST);
	        }

	        if (tasks.getDue_date()==null) {
	            
	            return new ResponseEntity<>("La fecha es un campo obligatorio", HttpStatus.BAD_REQUEST);
	        }
	        
	        if (tasks.getAssigned_to().equals("")) {
	            
	            return new ResponseEntity<>("El correo de asignación es un campo obligatorio", HttpStatus.BAD_REQUEST);
	        }
	        
	        
	      
			tasksService.save(tasks);
			emailService.enviarNotificacionRegistro(tasks.getAssigned_to(),tasks.getTitle(),tasks.getDue_date(),tasks.getAssigned_to(),tasks.getStatus());
			return new ResponseEntity<>(tasks,HttpStatus.OK);
		}
	

	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var ListaTasks = tasksService.findAll();
		return new ResponseEntity<>(ListaTasks, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findOne ( @PathVariable String id ){
		var tasks= tasksService.findOne(id);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody tasks tasksUpdate) {
	    
		// Verificar si hay campos vacíos
		
		if (tasksUpdate.contieneCamposVacios()) {
			return new ResponseEntity<>("Todos los campos son obligatorios", HttpStatus.BAD_REQUEST);
		}

		var tasks = tasksService.findOne(id).get();
		if (tasks != null) {
			  // Verificar si el titulo se está cambiando
	        if (!tasks.getTitle().equals(tasksUpdate.getTitle())) {
	            // El titulo se está cambiando, verificar si ya está en uso
	            List<tasks> taskss_con_mismo_titulo = tasksService.filtroIngresoTasks(tasksUpdate.getTitle());
	            if (!taskss_con_mismo_titulo.isEmpty()) {
	                // Si hay otros taskss con el mismo número de documento, devuelve un error
	                return new ResponseEntity<>("El tasks ya está registrado", HttpStatus.BAD_REQUEST);
	            }
	        }


			tasks.setTitle(tasksUpdate.getTitle());
			tasks.setDue_date(tasksUpdate.getDue_date());
			tasks.setAssigned_to(tasksUpdate.getAssigned_to());
			tasks.setStatus(tasksUpdate.getStatus());

			tasksService.save(tasks);
			return new ResponseEntity<>("Guardado", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Error tasks no encontrado", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>delete (@PathVariable("id")String id){
		tasksService.delete(id);
		return new ResponseEntity<>("Tarea eliminada",HttpStatus.OK);
	}

}
