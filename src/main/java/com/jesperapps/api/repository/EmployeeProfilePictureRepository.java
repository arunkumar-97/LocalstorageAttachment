package com.jesperapps.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.api.model.EmployeeProfilePicture;

public interface EmployeeProfilePictureRepository extends JpaRepository<EmployeeProfilePicture, Integer> {
	EmployeeProfilePicture findByPictureId(Integer pictureId);
}