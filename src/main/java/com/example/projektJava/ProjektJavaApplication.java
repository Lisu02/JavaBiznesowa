package com.example.projektJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProjektJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektJavaApplication.class, args);
	}

}
