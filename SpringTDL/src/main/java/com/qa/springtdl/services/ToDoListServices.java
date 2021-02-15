package com.qa.springtdl.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.springtdl.persistance.domain.ToDoListDomain;
import com.qa.springtdl.persistance.dto.ToDoListDTO;




@Service
public class ToDoListServices {

	

	private ToDoListRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public ToDoListServices(ToDoListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	private ToDoListDTO mapDTO(ToDoListDomain model) {
		return this.mapper.map(model, ToDoListDTO.class);

	}

	// GET
	//readOne
	
	public ToDoListDTO readOne(Long id) {
		return mapDTO(this.repo.findById(id).orElseThrow());
		
	}
	
	
	
	//readall
	public List<ToDoListDTO> readall() {
		List<ToDoListDomain> dblist = this.repo.findAll();
		List<ToDoListDTO> resultlist = dblist.stream().map(this::mapDTO).collect(Collectors.toList());
        return resultlist;
	}

	// POST

	public ToDoListDTO create(ToDoListDomain todolist) {
		return this.mapDTO(this.repo.save(todolist));

	}
	//Put
	
		public ToDoListDTO update(Long id,ToDoListDomain newDetails) {
			ToDoListDomain dbEntry = this.repo.findById(id).orElseThrow();
			newDetails.setList_id(id); 
			ToDoListDTO result = this.mapDTO(this.repo.save(newDetails));
			return result;
		}

	//Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
