package com.jesperapps.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.jesperapps.api.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	Employee findByEmployeeId(Integer employeeId);

//	Employee findByEmployeeId(Integer employeeId);

}
