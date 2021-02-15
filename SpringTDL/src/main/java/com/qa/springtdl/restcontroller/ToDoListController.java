package com.qa.springtdl.restcontroller;

import java.util.ArrayList;
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

import com.qa.springtdl.persistance.domain.ToDoListDomain;
import com.qa.springtdl.persistance.dto.ToDoListDTO;
import com.qa.springtdl.services.ToDoListServices;

@RestController
@RequestMapping("/ToDoList")
public class ToDoListController {
	
	private ToDoListServices service;
	
	@Autowired
	public ToDoListController(ToDoListServices service) {
		super();
		this.service = service;
		}
	//List declaration
		private List<ToDoListDomain> tdllist = new ArrayList<>();
		// GET read all
		@GetMapping("/readAll")
		public ResponseEntity<List<ToDoListDTO>> readAll() {

			return new ResponseEntity<List<ToDoListDTO>>(this.service.readall(), HttpStatus.OK);
		}

		// Get
		@GetMapping("/read/{id}")
		public ResponseEntity<ToDoListDTO> readOne(@PathVariable("id") Long id) {
			return new ResponseEntity<ToDoListDTO>(this.service.readOne(id), HttpStatus.OK);
		}

	//POST
		@PostMapping("/create")
		public ResponseEntity<ToDoListDTO> create(@RequestBody ToDoListDomain todolist) {
			return new ResponseEntity<ToDoListDTO>(this.service.create(todolist), HttpStatus.CREATED);

		}

	//PUT
		@PutMapping("/replace/{id}")
		public ResponseEntity<ToDoListDTO> replace(@PathVariable Long id, @RequestBody ToDoListDomain todolist) {
			return new ResponseEntity<ToDoListDTO>(this.service.update(id,todolist), HttpStatus.ACCEPTED);
		}
		

	//DELETE
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<ToDoListDTO> delete(@PathVariable("id") Long id) {
			return this.service.delete(id)?
					new ResponseEntity<>(HttpStatus.NO_CONTENT):
						new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		

}
