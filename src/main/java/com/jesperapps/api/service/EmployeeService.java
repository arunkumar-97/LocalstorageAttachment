package com.jesperapps.api.service;

import com.jesperapps.api.JSONmodel.AttachmentJSON;
import com.jesperapps.api.JSONmodel.EmployeeJSON;
import com.jesperapps.api.model.Employee;
import com.jesperapps.api.model.EmployeeProfilePicture;

public interface EmployeeService {

	Employee addEmployee(EmployeeJSON employeeRequest);

	void saveEmployee(Employee idFromDb);

	Employee getEmployeeId(Integer employeeId);

	EmployeeProfilePicture savePicture(AttachmentJSON newProfilePicture);

}
