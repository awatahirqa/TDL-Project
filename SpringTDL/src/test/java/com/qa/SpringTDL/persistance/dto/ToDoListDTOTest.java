package com.qa.SpringTDL.persistance.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.qa.TDL.persistance.dto.ToDoListDTO;

@SpringBootTest
public class ToDoListDTOTest {
private ToDoListDTO todolistdto;



@Test
public void setterGetterTest() {
	todolistdto = new ToDoListDTO(1L, "Test List",null);
	
	
	Assertions.assertNotNull(todolistdto);
	todolistdto.setList_id(1L);
	todolistdto.setTasklist(null);
	todolistdto.setName("Test List");
	Assertions.assertNotNull(todolistdto.getList_id());
	Assertions.assertNull(todolistdto.gettasklist());
	Assertions.assertNotNull(todolistdto.getName());
	
}

}
