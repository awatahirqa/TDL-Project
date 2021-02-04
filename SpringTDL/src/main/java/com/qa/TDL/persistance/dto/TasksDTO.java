package com.qa.TDL.persistance.dto;

import javax.persistence.ManyToOne;

import org.springframework.format.datetime.standard.DateTimeContext;

import com.qa.TDL.persistance.domain.ToDoListDomain;

public class TasksDTO {
	
	
    private Long TaskId;
	private String summary;
	private int priority;
	private DateTimeContext deadline;
	private ToDoListDomain myList;
	private String status;
	
	
	public TasksDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TasksDTO(Long taskId, String summary, int priority, DateTimeContext deadline, ToDoListDomain myList,
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
	public DateTimeContext getDeadline() {
		return deadline;
	}
	public void setDeadline(DateTimeContext deadline) {
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
