package com.jesperapps.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.api.JSONmodel.AttachmentJSON;
import com.jesperapps.api.model.EmployeeProfilePicture;
import com.jesperapps.api.repository.EmployeeProfilePictureRepository;



@Service
public  class AttachmentServicelmpl implements AttachmentService{
				
	public static final String location="C:\\Users\\Guest_user\\OrganizationProfilePicture";
	
	@Autowired
	private EmployeeProfilePictureRepository employeeProfilePictureRepository;

	@Override
	public EmployeeProfilePicture saveFile(AttachmentJSON ProfilePicture) {
		EmployeeProfilePicture newPicture = new EmployeeProfilePicture();
		if(this.saveFileFromReq(ProfilePicture)) {
			newPicture.setPictureName(ProfilePicture.getAttachmentName());
			return newPicture;
		}
		return null;
    }
	

	private boolean saveFileToLocalDisk(String attachmentName, String fileByte) {
		   try{
	            if(Files.notExists(Paths.get(location))){
	                File directoryFile = new File(location);
	                if(directoryFile.mkdir()){
	                    //directory created successfully;
	                }else{
	                    //error creating directory
	                }
	            }
	            if(
	            		(attachmentName != null && attachmentName.length() > 0)
	            		&&
	            		(fileByte != null && fileByte.length() > 0)
	            		){
	                File newProfilePicture = new File(location + "\\"+ attachmentName);
					OutputStream buffer = new FileOutputStream(newProfilePicture);
					buffer.write(Base64.decodeBase64(fileByte));
					buffer.close();
	            }
	            else{
	                return false;
	            }
	        }catch(Exception e){
	           
	            return false;
	        }
	        return true;
	}


	private boolean saveFileFromReq(AttachmentJSON updateAttachmentRequest) {
		if(this.saveFileToLocalDisk(updateAttachmentRequest.getAttachmentName(), updateAttachmentRequest.getFileByte()))
		{
			return true;
		}
		return false;
	}


	private boolean deleteFileFromLocalStorage(String pictureName) {
		try {
			Files.delete(Paths.get(location+"\\"+pictureName));
		}catch(Exception e) {
			return false;
		}
		return true;
	}


	@Override
	public boolean updateAttachmentDetails(EmployeeProfilePicture attachmentFromDb,
			AttachmentJSON updateAttachmentRequest) {
		if(this.deleteFileFromLocalStorage(attachmentFromDb.getPictureName())) {
			if(this.saveFileFromReq(updateAttachmentRequest)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public byte[] getFileBytes(String pictureName) {
		try {
			return Files.readAllBytes(Paths.get(this.location+"\\"+pictureName));
		}catch(IOException e) {
		}
		return new byte[] {};
	}


	@Override
	public void saveToDb(EmployeeProfilePicture newPicture) {
		if(newPicture != null){
			this.employeeProfilePictureRepository.save(newPicture);
		}
	}


	@Override
	public EmployeeProfilePicture getByPictureId(Integer pictureId) {
		return this.employeeProfilePictureRepository.findByPictureId(pictureId);
	}






	


	
}
