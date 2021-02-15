package com.qa.springtdl.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.springtdl.persistance.domain.TasksDomain;
import com.qa.springtdl.persistance.dto.TasksDTO;
import com.qa.springtdl.services.TaskRepo;
import com.qa.springtdl.services.TasksServices;
 
@SpringBootTest
public class TasksServicesTest {
	@MockBean
	private ModelMapper mockedMapper;
	
	@MockBean
	private TaskRepo mockedRepo;
	
	@Autowired
	private TasksServices service;
	@Test
	public void create() {
		//Resources 
		TasksDomain testtask = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", "Ongoing");
		TasksDTO testdto = new TasksDTO(1L, "Simple task to test my domain", 2, "01-01-2021", "Ongoing");
		//Rules
		Mockito.when(this.mockedRepo.save(Mockito.any(TasksDomain.class))).thenReturn(testtask);
		Mockito.when(this.mockedMapper.map(testtask, TasksDTO.class)).thenReturn(testdto);
		//Action
		TasksDTO result = this.service.create(testtask);
		//Assertions
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result,testdto);
		Assertions.assertEquals(result,testdto);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(TasksDomain.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testtask, TasksDTO.class);
	}
	
	@Test
	public void delete() {
		TasksDomain testtask = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		Long id = 1l;
		
		Mockito.when(this.mockedRepo.existsById(id)).thenReturn(false);
		
		Assertions.assertTrue(this.service.delete(id));
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(id);
	}
	
	@Test
	public void readById() {
		TasksDomain testtask = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		TasksDTO testdto = this.mockedMapper.map(testtask, TasksDTO.class);
		
		Mockito.when(this.mockedRepo.findById(testtask.getTaskId())).thenReturn(Optional.of(testtask));
		TasksDTO result = this.service.readOne(1L);
		
		Assertions.assertEquals(result,testdto);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
		}
	
	@Test
	public void readAll() {
		Long id = 1L;
		TasksDomain testtask = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		
		
		testtask.setTaskId(id);
		
		List<TasksDomain>tasks = this.mockedRepo.findAll();
		TasksDTO resultList = this.mockedMapper.map(tasks, TasksDTO.class);
		
		Mockito.when(this.mockedRepo.findAll()).thenReturn(tasks);
		Mockito.when(this.mockedMapper.map(tasks, TasksDTO.class)).thenReturn(resultList);
		

		
		Assertions.assertNotNull(tasks);
		Assertions.assertEquals(this.service.readall(),tasks);
		
		Mockito.verify(this.mockedRepo, Mockito.times(2)).findAll();
		

	}
	
	@Test
	public void update() {
		TasksDomain testtaskA = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		TasksDomain testtaskB = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		TasksDTO testdto = new TasksDTO(1L, testtaskB.getSummary(), testtaskB.getPriority(), testtaskB.getDeadline(), testtaskB.getStatus());
		
		Mockito.when(this.mockedRepo.findById(1L)).thenReturn(Optional.of(testtaskA));
		Mockito.when(this.mockedRepo.save(testtaskB)).thenReturn(testtaskB);
		Mockito.when(this.mockedMapper.map(testtaskB, TasksDTO.class)).thenReturn(testdto);
		
		TasksDTO result = this.service.update(1L, testtaskB);
		
		Assertions.assertEquals(result,testdto);
	}
	
}
