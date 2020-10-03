package com.jesperapps.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.api.JSONmodel.AttachmentJSON;
import com.jesperapps.api.JSONmodel.EmployeeJSON;
import com.jesperapps.api.model.Employee;
import com.jesperapps.api.model.EmployeeProfilePicture;
import com.jesperapps.api.repository.EmployeeRepository;

@Service
public class EmployeeServicelmpl implements EmployeeService {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;

	
	@Autowired
	private AttachmentService attachmentService;

	@Override
	public Employee addEmployee(EmployeeJSON employeeRequest) {
		Employee employee=new Employee();
		employee.setEmployeeId(employeeRequest.getEmployeeId());
		employee.setEmployeeName(employeeRequest.getEmployeeName());
		employee.setStatus(employeeRequest.getStatus());
		EmployeeProfilePicture newPictureToSaveOnDB =null;
		AttachmentJSON pictureFromRequest = employeeRequest.getImage();
		if(pictureFromRequest != null) {
			newPictureToSaveOnDB = this.savePicture(pictureFromRequest);	
			if(newPictureToSaveOnDB != null) {
				newPictureToSaveOnDB.setEmployee(employee);
				employee.setEmployeeProfilePicture(newPictureToSaveOnDB);
			}
		}
		employeeRepository.save(employee);
		if(newPictureToSaveOnDB != null) {
			newPictureToSaveOnDB.setPictureLocation(AttachmentService.URL+newPictureToSaveOnDB.getPictureId());
			attachmentService.saveToDb(newPictureToSaveOnDB);
		}
		return employee;
	}

	@Override
	public void saveEmployee(Employee idFromDb) {
		employeeRepository.save(idFromDb);
	}

	@Override
	public Employee getEmployeeId(Integer employeeId) {
		return employeeRepository.findByEmployeeId(employeeId);
	}

	@Override
	public EmployeeProfilePicture savePicture(AttachmentJSON newProfilePicture) {
		EmployeeProfilePicture pictureToSaveOnDb = attachmentService.saveFile(newProfilePicture);
		return pictureToSaveOnDb;
	}

	
		
	}

