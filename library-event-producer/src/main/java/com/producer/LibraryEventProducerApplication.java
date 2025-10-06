package com.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class LibraryEventProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryEventProducerApplication.class, args);
		log.info("Application Started "+ LocalDateTime.now());
		System.out.println("<----------------Application Started--------------->");
	}
}
