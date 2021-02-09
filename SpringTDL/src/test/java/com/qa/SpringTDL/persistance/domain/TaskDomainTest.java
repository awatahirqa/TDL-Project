package com.qa.SpringTDL.persistance.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.TDL.persistance.domain.TasksDomain;
@SpringBootTest
public class TaskDomainTest {
	
private TasksDomain task;

	
	@Test
	public void setterGetterTest() {
		task = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		
		
		Assertions.assertNotNull(task);
		task.setTaskId(1L);
		task.setSummary("Simple task to test my domain");
		task.setPriority(2);
		task.setDeadline("01-01-2021");
		task.setMyList(null);
		task.setStatus("ongoing");
		Assertions.assertNotNull(task.getTaskId());
		Assertions.assertNotNull(task.getSummary());
		Assertions.assertNotNull(task.getPriority());
		Assertions.assertNotNull(task.getDeadline());
		Assertions.assertNull(task.getMyList());
		Assertions.assertNotNull(task.getStatus());
		
		
		
	}

}
