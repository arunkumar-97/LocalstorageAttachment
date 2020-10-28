package com.jesperapps.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jesperapps.api.model.EmployeeProfilePicture;
import com.jesperapps.api.service.AttachmentService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
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
		if(pictureFromDB.getPictureName().toLowerCase().contains("mp4")) {
			return ResponseEntity.status(HttpStatus.OK)

					.header("Content-Type", "video/mp4")

					.body(new ByteArrayResource(attachmentService.getFileBytes(pictureFromDB.getPictureName())));
		}else {
			return ResponseEntity.status(HttpStatus.OK)
					.header("Content-Type", "png/jpeg")
					.contentType(MediaType.IMAGE_JPEG)
//					.header("Content-Disposition", "attachment; filename="+pictureFromDB.getPictureName())
					.body(new ByteArrayResource(attachmentService.getFileBytes(pictureFromDB.getPictureName())));
		}
		
		}
		return ResponseEntity.status(HttpStatus.OK).body("No picture found");
	}
}
