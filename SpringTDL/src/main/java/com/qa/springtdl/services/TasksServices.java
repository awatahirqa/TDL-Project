package com.qa.springtdl.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.springtdl.persistance.domain.TasksDomain;
import com.qa.springtdl.persistance.dto.TasksDTO;

@Service
public class TasksServices {

	private TaskRepo repo;
	private ModelMapper mapper;

	@Autowired
	public TasksServices(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private TasksDTO mapDTO(TasksDomain model) {
		return this.mapper.map(model, TasksDTO.class);

	}
	// GET
		//readOne
		
		public TasksDTO readOne(Long id) {
			return mapDTO(this.repo.findById(id).orElseThrow());
			
		}
		
		
		
		//readall
		public List<TasksDTO> readall() {
			List<TasksDomain> dblist = this.repo.findAll();
			List<TasksDTO> resultlist = dblist.stream().map(this::mapDTO).collect(Collectors.toList());
			//[1,2] stream breaks down list into each TasksDomain 
	        return resultlist;
		}

		// POST

		public TasksDTO create(TasksDomain task) {
			return this.mapDTO(this.repo.save(task));

		}
		//Put
		
			public TasksDTO update(Long id,TasksDomain newDetails) {
				TasksDomain dbEntry = this.repo.findById(id).orElseThrow();
				newDetails.setTaskId(id); 
				TasksDTO result = this.mapDTO(this.repo.save(newDetails));
				return result; 
			}

		//Delete
		public boolean delete(Long id) {
			this.repo.deleteById(id);
			return !this.repo.existsById(id);
		}

}
