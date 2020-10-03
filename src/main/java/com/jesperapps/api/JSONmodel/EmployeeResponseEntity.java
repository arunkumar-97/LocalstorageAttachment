package com.jesperapps.api.JSONmodel;

import com.jesperapps.api.model.Employee;


public class EmployeeResponseEntity {
	
	private Integer employeeId;
	private String employeeName;
	private String status;
	private String EmployeeProfilePicture;
	public EmployeeResponseEntity() {
		
	}
	
	public EmployeeResponseEntity(Employee createdEmployee) {
	
		this.employeeId=createdEmployee.getEmployeeId();
		this.employeeName=createdEmployee.getEmployeeName();
		this.status=createdEmployee.getStatus();
		if(createdEmployee.getEmployeeProfilePicture()!=null) {
		this.EmployeeProfilePicture=createdEmployee.getEmployeeProfilePicture().getPictureName();
		}
	}

	

	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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
	public String getEmployeeProfilePicture() {
		return EmployeeProfilePicture;
	}
	public void setEmployeeProfilePicture(String employeeProfilePicture) {
		EmployeeProfilePicture = employeeProfilePicture;
	}
	
	

}
