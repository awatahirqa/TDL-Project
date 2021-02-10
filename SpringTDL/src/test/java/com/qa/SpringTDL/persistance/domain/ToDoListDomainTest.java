package com.qa.springtdl.persistance.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.springtdl.persistance.domain.TasksDomain;
import com.qa.springtdl.persistance.domain.ToDoListDomain;

@SpringBootTest
public class ToDoListDomainTest {
	private ToDoListDomain todolist;
	

	@Test
	public void setterGetterTest() {
		todolist = new ToDoListDomain(1L,null, "Test List");
		
		
		Assertions.assertNotNull(todolist);
		todolist.setList_id(1L);
		todolist.setTasklist(null);
		todolist.setName("Test List");
		Assertions.assertNotNull(todolist.getList_id());
		Assertions.assertNull(todolist.gettasklist());
		Assertions.assertNotNull(todolist.getName());
		
		
		
	}
}
