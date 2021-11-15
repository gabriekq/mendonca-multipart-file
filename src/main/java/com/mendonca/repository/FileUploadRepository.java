package com.mendonca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mendonca.model.UploadedFile;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadedFile, String> {

	
	
	
	
	
}
