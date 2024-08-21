package com.springboot.retotecnico.application.ports.input;

import com.springboot.retotecnico.application.dto.StudentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentUseCase {
    Mono<Void> saveStudent(StudentDTO studentDto);
    Flux<StudentDTO> getAllActiveStudents();
    Flux<StudentDTO> getAllStudents();
}
