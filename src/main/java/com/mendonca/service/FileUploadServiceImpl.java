package com.mendonca.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mendonca.model.UploadedFile;
import com.mendonca.repository.FileUploadRepository;
import com.zaxxer.hikari.util.SuspendResumeLock;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	//private String uploadFolderPath ="/Users/Gabriel Mendonca/uploaded_";
	
	private Path currentRelativePath = Paths.get("");
	private String fullPath = currentRelativePath.toAbsolutePath().toString()+"\\upload_";

	@Autowired
	private FileUploadRepository fileUploadRepository;
	
	@Override
	public void uploadToLocal(MultipartFile file) {
		
		try {
			byte [] data = file.getBytes();
			Path path = Paths.get(fullPath+file.getOriginalFilename());
			System.out.println(fullPath+file.getOriginalFilename());
			Files.write(path, data);
		} catch (IOException e) {	
			System.out.println(e.toString());
		}
		
		
		
	}


	@Override
	public UploadedFile uploadToDb(MultipartFile multipartFile) {
		
		UploadedFile uploadedFile = new UploadedFile();
		try {
			uploadedFile.setFileData(multipartFile.getBytes());
			uploadedFile.setFileType( multipartFile.getContentType());
			uploadedFile.setFileName(multipartFile.getOriginalFilename());
			UploadedFile uploadedFileRet = fileUploadRepository.save(uploadedFile);
			return uploadedFileRet;
		}catch (IOException e) {
			System.out.println(e.toString());
			return null;
		}
			
		
		
		
	}


	@Override
	public UploadedFile downloadFile(String fileId) {
	
	   UploadedFile uploadedFileToRet = fileUploadRepository.getOne(fileId);
		return  uploadedFileToRet ;
	}

	
	
	
}
