package com.springboot.retotecnico.infrastructure.config;

import com.springboot.retotecnico.domain.exceptions.StudentFoundException;
import com.springboot.retotecnico.application.dto.ErrorResponseDTO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StudentExceptionHandler {

    @ExceptionHandler(StudentFoundException.class)
    @ResponseStatus
    public ResponseEntity<ErrorResponseDTO> studentFoundException(StudentFoundException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponseDTO resultException = ErrorResponseDTO.builder()
                .code(status.value())
                .error(status.getReasonPhrase())
                .message(Collections.singletonList(ex.getMessage()))
                .build();
        return new ResponseEntity<>(resultException, status);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus
    public ResponseEntity<ErrorResponseDTO> webExchangeBindException(WebExchangeBindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> lisErrors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

        ErrorResponseDTO resultException = ErrorResponseDTO.builder()
                .code(status.value())
                .error(status.getReasonPhrase())
                .message(lisErrors)
                .build();
        return new ResponseEntity<>(resultException, status);
    }
}
