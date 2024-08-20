package com.springboot.retotecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableR2dbcRepositories
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RetoTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoTecnicoApplication.class, args);
	}

}
