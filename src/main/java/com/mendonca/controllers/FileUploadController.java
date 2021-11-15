package com.mendonca.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mendonca.model.UploadedFile;
import com.mendonca.response.FileUploadResponse;
import com.mendonca.service.FileUploadService;

@RestController
@RequestMapping("api/v1")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService; 
	
	
	@PostMapping("/upload/local")
	public void uploadLocal(@RequestParam("file")MultipartFile multipartFile ) {
		fileUploadService.uploadToLocal(multipartFile);
	}
	
	@PostMapping("/upload/db")
    public FileUploadResponse uploadDB(@RequestParam("file")MultipartFile multipartFile ) {
	
		UploadedFile uploadedFile =	fileUploadService.uploadToDb(multipartFile);
		FileUploadResponse response = new FileUploadResponse();
		if(uploadedFile != null) {
			
			String downloadUri =ServletUriComponentsBuilder.fromCurrentContextPath()
					            .path("/api/v1/download/")
					            .path(uploadedFile.getFileId()).toUriString();
			                     
			System.out.println(downloadUri);
			response.setDownloadUri(downloadUri);
			response.setFileId(uploadedFile.getFileId());
			response.setFileType( uploadedFile.getFileType());
			response.setUploadStatus(true);
			response.setMessage("File upload successfully");
			return response;
		}
		response.setMessage("Error");
		return response;
	
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String id ) {
		UploadedFile uploadedFile =  fileUploadService.downloadFile(id);
		
		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(uploadedFile.getFileType()))
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= "+uploadedFile.getFileName())
			.body(new ByteArrayResource(uploadedFile.getFileData()));
	}
	
	
	
}
