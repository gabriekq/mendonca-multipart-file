package com.mendonca.service;

import org.springframework.web.multipart.MultipartFile;

import com.mendonca.model.UploadedFile;

public interface FileUploadService {

	public void uploadToLocal(MultipartFile multipartFile);
	
	public UploadedFile uploadToDb(MultipartFile multipartFile);
	
	public UploadedFile downloadFile(String fileId);
	
	
}
