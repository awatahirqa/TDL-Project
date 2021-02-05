package com.qa.TDL.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.TDL.persistance.domain.TasksDomain;
import com.qa.TDL.persistance.dto.TasksDTO;
import com.qa.TDL.services.TasksServices;

@RestController
@RequestMapping("/Tasks")
public class TasksController {

	private TasksServices service;
	
	@Autowired
	public TasksController(TasksServices service) {
		super();
		this.service = service;
	}
	
	// GET read all
	@GetMapping("/readAll")
	public ResponseEntity<List<TasksDTO>> readAll() {

		return new ResponseEntity<List<TasksDTO>>(this.service.readall(), HttpStatus.OK);
	}

	// Get read by id
	@GetMapping("/read/{id}")
	public ResponseEntity<TasksDTO> readOne(@PathVariable("id") Long id) {
		return new ResponseEntity<TasksDTO>(this.service.readOne(id), HttpStatus.OK);
	}

//POST
	@PostMapping("/create")
	public ResponseEntity<TasksDTO> create(@RequestBody TasksDomain task) {
		return new ResponseEntity<TasksDTO>(this.service.create(task), HttpStatus.CREATED);

	}

//PUT
	@PutMapping("/replace/{id}")
	public ResponseEntity<TasksDTO> replace(@PathVariable Long id, @RequestBody TasksDomain task) {
		return new ResponseEntity<TasksDTO>(this.service.update(id,task), HttpStatus.ACCEPTED);
	}
	

//DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		return this.service.delete(id)?
				new ResponseEntity<>(HttpStatus.NO_CONTENT):
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
