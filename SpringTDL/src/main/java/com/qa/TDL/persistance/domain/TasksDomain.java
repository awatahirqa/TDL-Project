package com.qa.TDL.persistance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class TasksDomain {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long TaskId;
	
	
	private String summary;
	private int priority;
	private String deadline;
	@ManyToOne
	private ToDoListDomain myList;
	private String status;
	public TasksDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TasksDomain(Long taskId, String summary, int priority, String deadline, ToDoListDomain myList,
			String status) {
		super();
		TaskId = taskId;
		this.summary = summary;
		this.priority = priority;
		this.deadline = deadline;
		this.myList = myList;
		this.status = status;
	}
	public Long getTaskId() {
		return TaskId;
	}
	public void setTaskId(Long taskId) {
		TaskId = taskId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public ToDoListDomain getMyList() {
		return myList;
	}
	public void setMyList(ToDoListDomain myList) {
		this.myList = myList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Tasksdomain [TaskId=" + TaskId + ", summary=" + summary + ", priority=" + priority + ", deadline="
				+ deadline + ", myList=" + myList + ", status=" + status + "]";
	}
	
	


}
