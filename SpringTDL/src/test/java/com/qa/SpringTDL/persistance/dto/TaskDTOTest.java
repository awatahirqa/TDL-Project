package com.qa.springtdl.persistance.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.springtdl.persistance.dto.TasksDTO;

@SpringBootTest
public class TaskDTOTest {

	private TasksDTO taskdto; 

	@Test
	public void setterGetterTest() {
		taskdto = new TasksDTO(1L, "Simple task to test my dto", 2, "01-01-2021", "Ongoing"); 
		
		Assertions.assertNotNull(taskdto);
		taskdto.setTaskId(1L);
		taskdto.setSummary("Simple task to test my dto");
		taskdto.setPriority(2);
		taskdto.setDeadline("01-01-2021");
		taskdto.setStatus("ongoing");
		Assertions.assertNotNull(taskdto.getTaskId());
		Assertions.assertNotNull(taskdto.getSummary());
		Assertions.assertNotNull(taskdto.getPriority());
		Assertions.assertNotNull(taskdto.getDeadline());
		Assertions.assertNotNull(taskdto.getStatus());
		
		
	}
	
	
}
