package com.springboot.retotecnico.application.ports.output;

import com.springboot.retotecnico.domain.dto.StudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepositoryPort {
    Mono<Void> saveStudent(StudentDto studentDto);
    Flux<StudentDto> getAllActiveStudents();
    Flux<StudentDto> getAllStudents();
}
