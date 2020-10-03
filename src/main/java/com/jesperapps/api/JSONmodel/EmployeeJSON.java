package com.jesperapps.api.JSONmodel;

import com.jesperapps.api.model.Employee;

public class EmployeeJSON {

	
	private Integer employeeId;
	private String employeeName;
	private String status;
	private AttachmentJSON image;
	
	
	public EmployeeJSON() {
		
	}
	
	public EmployeeJSON(Employee idFromDb) {
		this.employeeId=idFromDb.getEmployeeId();
		this.employeeName=idFromDb.getEmployeeName();
		this.status=idFromDb.getStatus();
		
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer empoyeeId) {
		this.employeeId = empoyeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AttachmentJSON getImage() {
		return image;
	}
	public void setImage(AttachmentJSON image) {
		this.image = image;
	}
	

	
	
	
	
}
