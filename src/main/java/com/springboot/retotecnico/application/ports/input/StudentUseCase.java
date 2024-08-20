package com.springboot.retotecnico.application.ports.input;

import com.springboot.retotecnico.domain.dto.StudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentUseCase {
    Mono<Void> saveStudent(StudentDto studentDto);
    Flux<StudentDto> getAllActiveStudents();
    Flux<StudentDto> getAllStudents();
}
