package com.jesperapps.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class EmployeeProfilePicture{
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pictureId;
    private String pictureName;
    private String pictureLocation;
    @OneToOne(mappedBy="employeeProfilePicture")
    private Employee employee;

    public Integer getPictureId(){
        return this.pictureId;
    }
    public String getPictureName(){
        return pictureName;
    }
    public void setPictureName(String pictureName){
        this.pictureName = pictureName;
    }
    public String getPictureLocation(){
        return this.pictureLocation;
    }
    public void setPictureLocation(String pictureLocation){
        this.pictureLocation = pictureLocation;
    }
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}
	
	public boolean isJPGPicture() {
		return this.getPictureName().contains("jpg");
	}
   
}
