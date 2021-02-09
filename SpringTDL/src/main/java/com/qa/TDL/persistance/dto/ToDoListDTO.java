package com.qa.TDL.persistance.dto;

import java.util.List;

import com.qa.TDL.persistance.domain.TasksDomain;

public class ToDoListDTO {
	
	
	private Long list_id;
	private String name;
	private List<TasksDomain> tasklist;
	public ToDoListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ToDoListDTO(Long list_id, String name, List<TasksDomain> tasklist) {
		super();
		this.list_id = list_id;
		this.name = name;
		this.tasklist = tasklist;
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

	public List<TasksDomain> gettasklist() {
		return tasklist;
	}
	public void setTasklist(List<TasksDomain> tasklist) {
		this.tasklist = tasklist;
	}

	@Override
	public String toString() {
		return "ToDoListDTO [list_id=" + list_id + ", name=" + name + ", tasklist=" + tasklist + "]";
	}
	
	
}
