package com.qa.springtdl.persistance.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class ToDoListDomain {
	@Id	 
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long list_id;
	private String name;
	@OneToMany(mappedBy = "myList", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<TasksDomain> tasklist;
	public ToDoListDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToDoListDomain(Long list_id, List<TasksDomain> tasklist,String name) {
		super();
		this.list_id = list_id;
		this.tasklist = tasklist;
		this.name = name;
	}
	
	public ToDoListDomain(Long list_id, String name) {
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
	public List<TasksDomain> gettasklist() {
		return tasklist;
	}
	public void setTasklist(List<TasksDomain> tasklist) {
		this.tasklist = tasklist;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ToDoListDomain [list_id=" + list_id + ", name=" + name + ", tasklist=" + tasklist + "]";
	}
	
	
	
}
