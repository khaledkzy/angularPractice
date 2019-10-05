package com.khaled.distributed.backend.injest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InjestApplication {


	private static final Logger log = LoggerFactory.getLogger(InjestApplication.class);

	@Autowired
	private BookRepository repository;


	public static void main(String[] args) {


		SpringApplication.run(InjestApplication.class, args);
		CreateBook l = new CreateBook();

	}



}
