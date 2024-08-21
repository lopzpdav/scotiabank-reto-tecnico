package com.springboot.retotecnico;

import com.springboot.retotecnico.application.dto.StudentDTO;
import com.springboot.retotecnico.domain.entities.StudentEntity;
import com.springboot.retotecnico.infrastructure.adapter.repository.StudentRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.springboot.retotecnico.application.constants.StudentConstants.API_REQUEST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@AutoConfigureWebTestClient
@SpringBootTest()
public class RetoTecnicoAdapterTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private StudentRepository studentRepository;

    StudentDTO studentDto;
    public enum TestResponseCodesStatusEnum {CREATED_201, OK_200, NO_CONTENT_204, BAD_REQUEST_400, CONFLICT_409,}

    @ParameterizedTest
    @EnumSource(TestResponseCodesStatusEnum.class)
    void save(TestResponseCodesStatusEnum testCodesStatusEnum) {
        switch (testCodesStatusEnum) {
            case CREATED_201:
                studentDto = StudentDTO.builder()
                        .id(100L)
                        .name("TestName")
                        .lastName("TestLastName")
                        .status("1")
                        .age(23)
                        .build();
                when(studentRepository.save(any(StudentEntity.class))).thenReturn(Mono.empty());
                when(studentRepository.findStudentById(anyLong())).thenReturn(Mono.empty());
                client.post().uri(API_REQUEST.concat("/save"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .body(Mono.just(studentDto), StudentDTO.class)
                        .exchange()
                        .expectStatus().isCreated();

            case CONFLICT_409:
                studentDto = StudentDTO.builder()
                        .id(100L)
                        .name("TestName")
                        .lastName("TestLastName")
                        .status("1")
                        .age(23)
                        .build();
                when(studentRepository.save(any(StudentEntity.class))).thenReturn(Mono.empty());
                when(studentRepository.findStudentById(anyLong())).thenReturn(Mono.just(StudentEntity.builder()
                        .id(100L)
                        .name("TestName")
                        .lastName("TestLastName")
                        .status("1")
                        .age(23)
                        .created(LocalDateTime.now())
                        .build()));
                client.post().uri(API_REQUEST.concat("/save"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .body(Mono.just(studentDto), StudentDTO.class)
                        .exchange()
                        .expectStatus().is4xxClientError();

                case BAD_REQUEST_400:
                studentDto = StudentDTO.builder()
                        .build();
                client.post().uri(API_REQUEST.concat("/save"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .body(Mono.just(studentDto), StudentDTO.class)
                        .exchange()
                        .expectStatus().is4xxClientError();
            default:

        }
    }

    @ParameterizedTest
    @EnumSource(TestResponseCodesStatusEnum.class)
    void getAllActiveStudents(TestResponseCodesStatusEnum testCodesStatusEnum) {
        switch (testCodesStatusEnum) {
            case OK_200:
                List<StudentEntity> listStudent = new ArrayList<>();
                listStudent.add(new StudentEntity(1L, "TestName", "TestLastName", "1", 18, LocalDateTime.now()));
                when(studentRepository.findAllByStatus(any())).thenReturn(Flux.fromIterable(listStudent));
                client.get()
                        .uri(API_REQUEST.concat("/active"))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBodyList(StudentDTO.class);

            case NO_CONTENT_204:
                when(studentRepository.findAllByStatus(any())).thenReturn(Flux.empty());
                client.get()
                        .uri(API_REQUEST.concat("/active"))
                        .exchange()
                        .expectStatus().isNoContent();
            default:

        }
    }

    @ParameterizedTest
    @EnumSource(TestResponseCodesStatusEnum.class)
    void getAllStudents(TestResponseCodesStatusEnum testCodesStatusEnum) {
        switch (testCodesStatusEnum) {
            case OK_200:
                List<StudentEntity> listStudent = new ArrayList<>();
                listStudent.add(new StudentEntity(1L, "TestName", "TestLastName", "1", 18, null));
                when(studentRepository.findAll()).thenReturn(Flux.fromIterable(listStudent));
                client.get()
                        .uri(API_REQUEST.concat("/all"))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBodyList(StudentDTO.class);

            case NO_CONTENT_204:
                when(studentRepository.findAll()).thenReturn(Flux.empty());
                client.get()
                        .uri(API_REQUEST.concat("/all"))
                        .exchange()
                        .expectStatus().isNoContent();
            default:

        }
    }
}
