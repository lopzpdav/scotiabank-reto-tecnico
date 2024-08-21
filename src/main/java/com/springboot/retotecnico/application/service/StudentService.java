package com.springboot.retotecnico.application.service;

import com.springboot.retotecnico.application.ports.input.StudentUseCase;
import com.springboot.retotecnico.application.ports.output.StudentRepositoryPort;
import com.springboot.retotecnico.application.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService implements StudentUseCase {

    private final StudentRepositoryPort studentRepository;

    @Override
    public Mono<Void> saveStudent(StudentDTO studentDto) {
        return studentRepository.saveStudent(studentDto);

    }

    @Override
    public Flux<StudentDTO> getAllActiveStudents() {
        return studentRepository.getAllActiveStudents();
    }

    @Override
    public Flux<StudentDTO> getAllStudents() {
        return studentRepository.getAllStudents();
    }
}
