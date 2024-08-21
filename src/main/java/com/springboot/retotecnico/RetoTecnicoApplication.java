package com.springboot.retotecnico;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API Documentation Code Challenge",
				version = "1.0.0"
		),
		servers = @Server(url = "${config.path}")
)
public class RetoTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoTecnicoApplication.class, args);
	}

}
