package com.springboot.retotecnico.infrastructure.adapter;

import com.springboot.retotecnico.application.ports.output.StudentRepositoryPort;
import com.springboot.retotecnico.domain.enums.StatusEnum;
import com.springboot.retotecnico.domain.exceptions.StudentFoundException;
import com.springboot.retotecnico.domain.dto.StudentDto;
import com.springboot.retotecnico.infrastructure.adapter.repository.StudentRepository;
import com.springboot.retotecnico.infrastructure.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepositoryPort {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public Mono<Void> saveStudent(StudentDto studentDto) {
        return studentRepository.findStudentById(studentDto.getId())
                .flatMap(existingStudent -> Mono.error(new StudentFoundException(String.format("Student with id <%s> already exists", studentDto.getId()))))
                .switchIfEmpty(studentRepository.save(studentMapper.toEntity(studentDto)))
                .then();
    }

    @Override
    public Flux<StudentDto> getAllActiveStudents() {
        return studentRepository.findAllByStatus(StatusEnum.ACTIVE.getCod())
                .map(studentMapper::toDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No active students found")));
    }

    @Override
    public Flux<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .map(studentMapper::toDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No students found")));
    }
}
