package com.mendonca.multipartDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mendonca.service","com.mendonca.controllers","com.mendonca.exception"})
@EntityScan(basePackages = {"com.mendonca.model"})
@EnableJpaRepositories(basePackages= {"com.mendonca.repository"})
public class MultipartDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipartDemoApplication.class, args);
	}

}
