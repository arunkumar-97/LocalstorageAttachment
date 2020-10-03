package com.jesperapps.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;
	private String employeeName;
	private String status;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="EmployeeProfilePicture")
	private EmployeeProfilePicture employeeProfilePicture;
	
	
	
	
	
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
	public EmployeeProfilePicture getEmployeeProfilePicture() {
		return employeeProfilePicture;
	}
	public void setEmployeeProfilePicture(EmployeeProfilePicture employeeProfilePicture) {
		this.employeeProfilePicture = employeeProfilePicture;
	}
	
	
	
	
	
	
	
}
