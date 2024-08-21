package com.springboot.retotecnico.infrastructure.adapter;

import com.springboot.retotecnico.application.ports.output.StudentRepositoryPort;
import com.springboot.retotecnico.application.enums.StatusEnum;
import com.springboot.retotecnico.domain.exceptions.StudentFoundException;
import com.springboot.retotecnico.application.dto.StudentDTO;
import com.springboot.retotecnico.infrastructure.adapter.repository.StudentRepository;
import com.springboot.retotecnico.application.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.springboot.retotecnico.application.constants.StudentConstants.NO_ACTIVE_FOUND_MESSAGE;
import static com.springboot.retotecnico.application.constants.StudentConstants.NO_STUDENTS_FOUND_MESSAGE;
import static com.springboot.retotecnico.application.constants.StudentConstants.STUDENT_FOUND_MESSAGE;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepositoryPort {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public Mono<Void> saveStudent(StudentDTO studentDto) {
        return studentRepository.findStudentById(studentDto.getId())
                .flatMap(existingStudent -> Mono.error(new StudentFoundException(String.format(STUDENT_FOUND_MESSAGE, studentDto.getId()))))
                .switchIfEmpty(studentRepository.save(studentMapper.toEntity(studentDto)))
                .then();
    }

    @Override
    public Flux<StudentDTO> getAllActiveStudents() {
        return studentRepository.findAllByStatus(StatusEnum.ACTIVE.getCod())
                .map(studentMapper::toDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, NO_ACTIVE_FOUND_MESSAGE)));
    }

    @Override
    public Flux<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .map(studentMapper::toDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, NO_STUDENTS_FOUND_MESSAGE)));
    }
}
