package com.qa.TDL.persistance.domain;

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
	@OneToMany(mappedBy = "myList", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<TasksDomain> tasklist;
	public ToDoListDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToDoListDomain(Long list_id, List<TasksDomain> catlist) {
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
