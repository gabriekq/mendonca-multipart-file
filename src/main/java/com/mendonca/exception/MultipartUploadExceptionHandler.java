package com.mendonca.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class MultipartUploadExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleFileUploadException(MaxUploadSizeExceededException excption,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ) {
		
		return "file size over 128KB";
	}
	
	
	
}
