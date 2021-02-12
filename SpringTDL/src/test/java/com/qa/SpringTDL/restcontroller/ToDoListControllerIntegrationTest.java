package com.qa.springtdl.restcontroller;

import java.util.ArrayList;
import java.util.List;

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
		
		ToDoListDTO expectedResultA = new ToDoListDTO(1L, "Test List",null);
		ToDoListDTO expectedResultB = new ToDoListDTO(2L, "Pokemon List", null);
		
		List<ToDoListDTO>ToDoListDTOList = new ArrayList<ToDoListDTO>();
		
		ToDoListDTOList.add(expectedResultA);
		ToDoListDTOList.add(expectedResultB);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "http://localhost:8080/todolist/readAll").accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(ToDoListDTOList));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		
	}
	
	@Test
	public void create() throws Exception{ 
		
		ToDoListDomain contentBody = new ToDoListDomain(1L,null, "Test List");
		ToDoListDTO expectedResult = mapToDTO(contentBody);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "http://localhost:8080/todolist/create")
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	// PUT
		@Test
		public void updateToDoList() throws Exception {
			// resources
			ToDoListDomain contentBody = new ToDoListDomain(1L,null, "Test List");
			ToDoListDTO expectedResult = this.mapToDTO(contentBody);
			
			// set up request
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "http://localhost:8080/todolist/update"+ ID)
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
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "http://localhost:8080/todolist/delete/" + ID);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest).andExpect(matchStatus);

	}

}
