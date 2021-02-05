package com.qa.TDL.persistance.dto;

import java.util.List;

import com.qa.TDL.persistance.domain.TasksDomain;

public class ToDoListDTO {
	
	
	private Long list_id;
	private List<TasksDomain> tasklist;
	public ToDoListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToDoListDTO(Long list_id, List<TasksDomain> catlist) {
		super();
		this.list_id = list_id;
		this.tasklist = catlist;
	}
	public Long getList_id() {
		return list_id;
	}
	public void setList_id(Long list_id) {
		this.list_id = list_id;
	}
	public List<TasksDomain> getCatlist() {
		return tasklist;
	}
	public void setCatlist(List<TasksDomain> catlist) {
		this.tasklist = catlist;
	}
	@Override
	public String toString() {
		return "ToDoListDomain [list_id=" + list_id + ", catlist=" + tasklist + "]";
	}
	
}
