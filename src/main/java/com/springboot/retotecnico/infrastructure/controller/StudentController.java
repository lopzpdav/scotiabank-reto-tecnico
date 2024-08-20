package com.springboot.retotecnico.infrastructure.controller;

import com.springboot.retotecnico.application.ports.input.StudentUseCase;
import com.springboot.retotecnico.domain.dto.StudentDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.springboot.retotecnico.domain.constants.StudentConstants.API_REQUEST;

@Slf4j
@RestController
@RequestMapping(API_REQUEST)
@Validated
@RequiredArgsConstructor
public class StudentController {
    private final StudentUseCase studentUseCase;

    @PostMapping("/save")
    @Operation(summary = "Registra estudiantes", description = "Endpoint para registrar estudiantes")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> saveStudent (@Validated @RequestBody StudentDto request){
        return studentUseCase.saveStudent(request);
    }

    @GetMapping ("/active")
    @Operation(summary = "Consulta estudiantes activos", description = "Endpoint para consultar estudiantes con estado Activo")
    @ResponseStatus(HttpStatus.OK)
    public Flux<StudentDto> getAllActiveStudents(){
        return studentUseCase.getAllActiveStudents();
    }

    @GetMapping ("/all")
    @Operation(summary = "Consulta estudiantes", description = "Endpoint para consultar la totalidad de estudiantes registrados")
    @ResponseStatus(HttpStatus.OK)
    public Flux<StudentDto> getAllStudents(){
        return studentUseCase.getAllStudents();
    }
}
