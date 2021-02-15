package com.qa.springtdl.persistance.dto;

import java.util.List;

import com.qa.springtdl.persistance.domain.TasksDomain;

public class ToDoListDTO {
	
	
	private Long list_id;
	private String name;
	private List<TasksDTO> tasklist;
	public ToDoListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ToDoListDTO(Long list_id, String name, List<TasksDTO> tasklist) {
		super();
		this.list_id = list_id;
		this.name = name;
		this.tasklist = tasklist;
	}
	public ToDoListDTO(Long list_id, String name) {
		super();
		this.list_id = list_id;
		this.name = name;
		
	}
	

	public Long getList_id() {
		return list_id;
	}
	public void setList_id(Long list_id) {
		this.list_id = list_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TasksDTO> gettasklist() {
		return tasklist;
	}
	public void setTasklist(List<TasksDTO> tasklist) {
		this.tasklist = tasklist;
	}

	@Override
	public String toString() {
		return "ToDoListDTO [list_id=" + list_id + ", name=" + name + ", tasklist=" + tasklist + "]";
	}
	
	
}
