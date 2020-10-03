package com.jesperapps.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.api.JSONmodel.AttachmentJSON;
import com.jesperapps.api.JSONmodel.EmployeeJSON;
import com.jesperapps.api.JSONmodel.EmployeeResponseEntity;
import com.jesperapps.api.model.Employee;
import com.jesperapps.api.model.EmployeeProfilePicture;
import com.jesperapps.api.service.AttachmentService;
import com.jesperapps.api.service.EmployeeService;



@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/employee")
	private ResponseEntity createEmployee(@RequestBody EmployeeJSON employeeRequest) {
//		System.out.println(employeeRequest.getEmpoyeeId());
		Employee createdEmployee=employeeService.addEmployee(employeeRequest);
		if(createdEmployee != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EmployeeResponseEntity(createdEmployee));
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new EmployeeResponseEntity() );
	
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@PostMapping("/employee/{employeeId}/profile")
	private ResponseEntity updateEmployeeProfilePicture(@PathVariable Integer employeeId, 
			@RequestBody AttachmentJSON newPicture) {
		Employee employeeFromDB = employeeService.getEmployeeId(employeeId);
		if(employeeFromDB != null) {
			EmployeeProfilePicture employeeProfilePictureFromDB = employeeFromDB.getEmployeeProfilePicture();
			if(employeeProfilePictureFromDB != null) {
				if(attachmentService.updateAttachmentDetails(employeeProfilePictureFromDB, newPicture)) {
					employeeProfilePictureFromDB.setPictureName(newPicture.getAttachmentName());
					attachmentService.saveToDb(employeeProfilePictureFromDB);
					return ResponseEntity.status(HttpStatus.OK).body(new EmployeeResponseEntity(employeeFromDB));
				}
				else {
					employeeProfilePictureFromDB.setPictureName(newPicture.getAttachmentName());
					employeeFromDB.setEmployeeProfilePicture(employeeProfilePictureFromDB);
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EmployeeResponseEntity(employeeFromDB));
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new EmployeeResponseEntity());
	}
	

	
	
}
