package com.springboot.retotecnico.infrastructure.adapter.repository;

import com.springboot.retotecnico.domain.entities.StudentEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends R2dbcRepository<StudentEntity, Long> {
    Flux<StudentEntity> findAllByStatus(String status);
    Mono<StudentEntity> findStudentById(Long id);
}
