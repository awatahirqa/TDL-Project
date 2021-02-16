package com.qa.springtdl.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springtdl.persistance.domain.TasksDomain;
import com.qa.springtdl.persistance.domain.ToDoListDomain;
import com.qa.springtdl.persistance.dto.ToDoListDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ToDoListControllerIntegrationTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier; 
	
	private final int ID = 1;
	
	private ToDoListDTO mapToDTO(ToDoListDomain model) {
		return this.mapper.map(model, ToDoListDTO.class);
	}
	

	@Test
	public void readAll() throws Exception{
		
		ToDoListDomain expectedResultA = new ToDoListDomain(1L, "Food");
		TasksDomain expectedtaskA = new TasksDomain (1L,"need to pick up veg",3,"07/03/21","Ongoing");
		TasksDomain expectedtaskB  = new TasksDomain  (2L,"Got to catch them all",1,"11/03/21","Ongoing");
		TasksDomain expectedtaskC   = new TasksDomain  (3l,"need to pick up meat",2,"07/03/21","Ongoing");
		List<TasksDomain> Tasklist = List.of(expectedtaskA,expectedtaskB,expectedtaskC);
				
		List<ToDoListDomain>ToDoListDTOList = new ArrayList<>();
		expectedResultA.setTasklist(Tasklist);
		ToDoListDTOList.add(expectedResultA);
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET ,"/ToDoList/readAll").accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(ToDoListDTOList.stream().map(this::mapToDTO).collect(Collectors.toList())));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		
	}
	
	@Test
	public void create() throws Exception{ 
		
		ToDoListDomain contentBody = new ToDoListDomain(2L,null, "Test");
		ToDoListDTO expectedResult = mapToDTO(contentBody);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/ToDoList/create")
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	// PUT
		@Test
		public void updateToDoList() throws Exception {
			// resources
			ToDoListDomain contentBody = new ToDoListDomain(1L,null, "Food");
			ToDoListDTO expectedResult = this.mapToDTO(contentBody);
			
			// set up request
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/ToDoList/replace/"+ 1L)
					.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);
			
			// set up expectations
			ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
			ResultMatcher matchContent = MockMvcResultMatchers.content()
					.json(jsonifier.writeValueAsString(expectedResult));
			
			// perform
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		}
	@Test
	public void delete() throws Exception{
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/ToDoList/delete/" + 1);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest).andExpect(matchStatus);

	}

}
