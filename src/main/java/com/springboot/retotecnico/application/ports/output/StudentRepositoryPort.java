package com.springboot.retotecnico.application.ports.output;

import com.springboot.retotecnico.application.dto.StudentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepositoryPort {
    Mono<Void> saveStudent(StudentDTO studentDto);
    Flux<StudentDTO> getAllActiveStudents();
    Flux<StudentDTO> getAllStudents();
}
