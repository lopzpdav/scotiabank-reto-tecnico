package com.springboot.retotecnico;

import com.springboot.retotecnico.application.ports.input.StudentUseCase;
import com.springboot.retotecnico.domain.dto.StudentDto;
import com.springboot.retotecnico.infrastructure.adapter.StudentRepositoryAdapter;
import com.springboot.retotecnico.infrastructure.controller.StudentController;
import com.springboot.retotecnico.infrastructure.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@SpringBootTest
class RetoTecnicoControllerTests {

	@InjectMocks
	StudentController studentController;

	@Mock
	StudentUseCase studentUseCase;

	@Mock
	StudentRepositoryAdapter studentRepositoryAdapter;

	@Mock
	StudentMapper studentMapper;

	@Test
	void saveStudentTest() {
		StudentDto studentDto = StudentDto.builder()
				.id(1L)
				.name("Patricio")
				.lastName("Lopez")
				.status("1")
				.age(12)
				.build();

		when(studentUseCase.saveStudent(eq(studentDto))).thenReturn(Mono.empty());

		WebTestClient
				.bindToController(studentController)
				.build()
				.post()
				.uri("/student/save")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(studentDto)
				.exchange()
				.expectStatus().is4xxClientError()
				.expectBody().isEmpty();
	}

	@Test
	void getAllActiveStudentsTest() {
		when(studentUseCase.getAllActiveStudents()).thenReturn(Flux.empty());

		WebTestClient
				.bindToController(studentController)
				.build()
				.get()
				.uri("/student/active")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	void getAllStudentsTest() {
		when(studentUseCase.getAllStudents()).thenReturn(Flux.empty());

		WebTestClient
				.bindToController(studentController)
				.build()
				.get()
				.uri("/student/all")
				.exchange()
				.expectStatus().isNotFound();
	}

}
