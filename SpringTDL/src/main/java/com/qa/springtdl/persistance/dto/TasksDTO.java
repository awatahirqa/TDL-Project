package com.qa.springtdl.persistance.dto;



import com.qa.springtdl.persistance.domain.ToDoListDomain;

public class TasksDTO {
	
	
    private Long TaskId;
	private String summary;
	private int priority;
	private String deadline;
	private String status;
	
	
	public TasksDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public TasksDTO(Long taskId, String summary, int priority, String deadline, String status) {
		super();
		TaskId = taskId;
		this.summary = summary;
		this.priority = priority;
		this.deadline = deadline;
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "TasksDTO [TaskId=" + TaskId + ", summary=" + summary + ", priority=" + priority + ", deadline="
				+ deadline + ", status=" + status + "]";
	}
	
	

}
