package com.jesperapps.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.api.model.EmployeeProfilePicture;
import com.jesperapps.api.service.AttachmentService;

@RestController
@RequestMapping(AttachmentService.BASE_URL)
public class EmployeeProfilePictureController {

	@Autowired
	private AttachmentService attachmentService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/{pictureId}")
	ResponseEntity viewProfilePicture(@PathVariable Integer pictureId) {
		EmployeeProfilePicture pictureFromDB = this.attachmentService.getByPictureId(pictureId);
		if(pictureFromDB != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(pictureFromDB.isJPGPicture() ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG)
					.body(attachmentService.getFileBytes(pictureFromDB.getPictureName()));
		}
		return ResponseEntity.status(HttpStatus.OK).body("No picture found");
	}
}
