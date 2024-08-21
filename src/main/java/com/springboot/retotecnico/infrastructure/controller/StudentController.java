package com.springboot.retotecnico.infrastructure.controller;

import com.springboot.retotecnico.application.ports.input.StudentUseCase;
import com.springboot.retotecnico.application.dto.StudentDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.springboot.retotecnico.application.constants.StudentConstants.API_REQUEST;

@Slf4j
@RestController
@RequestMapping(API_REQUEST)
@RequiredArgsConstructor
public class StudentController {
    private final StudentUseCase studentUseCase;

    @PostMapping("/save")
    @Operation(summary = "Registra estudiantes", description = "Endpoint para registrar estudiantes")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> saveStudent (@Validated @RequestBody StudentDTO request){
        return studentUseCase.saveStudent(request);
    }

    @GetMapping ("/active")
    @Operation(summary = "Consulta estudiantes activos", description = "Endpoint para consultar estudiantes con estado Activo")
    @ResponseStatus(HttpStatus.OK)
    public Flux<StudentDTO> getAllActiveStudents(){
        return studentUseCase.getAllActiveStudents();
    }

    @GetMapping ("/all")
    @Operation(summary = "Consulta estudiantes", description = "Endpoint para consultar la totalidad de estudiantes registrados")
    @ResponseStatus(HttpStatus.OK)
    public Flux<StudentDTO> getAllStudents(){
        return studentUseCase.getAllStudents();
    }
}
