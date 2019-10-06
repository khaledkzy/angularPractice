package com.khaled.distributed.backend.injest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InjestApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(InjestApplication.class);

	/**
	 * This creates an instance of the interface.
	 * Springboot will be trying to find something called BookRepository
	 * Then it will create it for us.
	 */
	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(InjestApplication.class, args);
	}

	@Override
	public void run(String... args) {

		log.info("StartApplication...");

		repository.save(new Book("Javax"));
		repository.save(new Book("Node"));
		repository.save(new Book("Python"));

		System.out.println("\nfindAll()");
		repository.findAll().forEach(x -> System.out.println(x));

		System.out.println("\nfindById(1L)");
		repository.findById(1l).ifPresent(x -> System.out.println(x));

		System.out.println("\nfindByName('Node')");
		repository.findByName("Node").forEach(x -> System.out.println(x));

	}

}
