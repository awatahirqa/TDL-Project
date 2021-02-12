package com.qa.springtdl.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springtdl.persistance.domain.TasksDomain;
import com.qa.springtdl.persistance.dto.TasksDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts =  {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TasksControllerIntegrationTest {
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier; 
	private TasksDTO mapToDTO(TasksDomain model) {
		return this.mapper.map(model, TasksDTO.class);
	}

	private final Long ID = 1L;
	
	@Test
	public void create() throws Exception{
		
		TasksDomain contentBody = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		TasksDTO expectedResult = mapToDTO(contentBody);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "http://localhost:8080/task/create")
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

}
	

	@Test
	public void readAll() throws Exception{
		
		
		TasksDTO expectedResultA = new TasksDTO(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		TasksDTO expectedResultB = new TasksDTO(2L, "Got to catch them all", 3, "02-02-2021", null, "Ongoing");
		TasksDTO expectedResultC = new TasksDTO(3L, "Assemble team to combat ineffeciency", 1, "03-03-2021", null, "Ongoing");
		
		List<TasksDTO>TasksDTOresultList = new ArrayList<TasksDTO>();
		
		TasksDTOresultList.add(expectedResultA);
		TasksDTOresultList.add(expectedResultB);
		TasksDTOresultList.add(expectedResultC);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "http://localhost:8080/tasks/readAll").accept(org.springframework.http.MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(TasksDTOresultList));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		
		
	}
	
	@Test
	public void readOne() throws Exception{
		
		TasksDTO expectedResult1 = new TasksDTO(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		//set it up
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "http://localhost:8080/tasks/read/" + ID);
		//set the expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult1));
		//perform the actions
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	
	
	
	@Test
	public void update() throws Exception{
		
		TasksDomain contentBody = new TasksDomain(1L, "Simple task to test my domain", 2, "01-01-2021", null, "Ongoing");
		TasksDTO expectedResult = mapToDTO(contentBody);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "http://localhost:8080/tasks/update/" + ID)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
}
	
	@Test
	public void delete() throws Exception{
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "http://localhost:8080/tasks/delete/" + ID);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest).andExpect(matchStatus);

	}
}
