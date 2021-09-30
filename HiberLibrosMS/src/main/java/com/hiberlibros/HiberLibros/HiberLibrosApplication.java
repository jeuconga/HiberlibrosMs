package com.hiberlibros.HiberLibros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HiberLibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiberLibrosApplication.class, args);
	}

}
