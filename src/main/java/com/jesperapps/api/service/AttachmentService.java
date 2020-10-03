package com.jesperapps.api.service;


import com.jesperapps.api.JSONmodel.AttachmentJSON;
import com.jesperapps.api.model.EmployeeProfilePicture;


public interface AttachmentService {
	
	static final String BASE_URL = "/profile";
	
	static final String URL = BASE_URL+ "/";

	EmployeeProfilePicture saveFile(AttachmentJSON profileAttachment);

	boolean updateAttachmentDetails(EmployeeProfilePicture attachmentFromDb, AttachmentJSON updateAttachmentRequest);
	
	byte[] getFileBytes(String pictureName);
	
	void saveToDb(EmployeeProfilePicture newPicture);
	
	EmployeeProfilePicture getByPictureId(Integer pictureId);

}
