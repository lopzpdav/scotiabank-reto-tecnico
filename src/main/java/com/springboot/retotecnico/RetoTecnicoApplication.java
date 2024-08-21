package com.springboot.retotecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class RetoTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoTecnicoApplication.class, args);
	}

}
